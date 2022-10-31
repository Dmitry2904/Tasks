package by.dmitry.shelepen.task4.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class StormService {
    private static final Logger log = LogManager.getLogger(StormService.class);

    String stormName = "";
    boolean stormFilter = false;

    public Map<String, Integer> getStormsWithMaxSustainWindAfterYearAndEndsWith(String targetURL,
                                                                                int yearFilter,
                                                                                String suffix) {
        HashMap<String, Integer> stormMap = new HashMap<>();

        URL url = getUrl(targetURL);
        if (url == null) return stormMap;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("EP") || line.startsWith("CP")) {
                    int stormYear = getStormYearFromFirstPartOfRow(line);
                    if (stormYear < yearFilter) {
                        stormFilter = false;
                        continue;
                    }
                    checkStormNameForEndingWithSuffix(suffix, line);
                } else {
                    if (stormFilter) {
                        int maxWindSpeed = extractMaxSustainedWindSpeedFromStatisticsRow(line);
                        findMaxWindSpeedAndPutToMap(stormMap, maxWindSpeed);
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
        return stormMap;
    }

    private int getStormYearFromFirstPartOfRow(String line) {
        String yearString = line.substring(4, 8);
        return Integer.parseInt(yearString);
    }

    private int extractMaxSustainedWindSpeedFromStatisticsRow(String line) {
        String[] array = line.split(",");
        String maxSustainedWindValue = array[6].trim();
        return Integer.parseInt(maxSustainedWindValue);
    }

    private void findMaxWindSpeedAndPutToMap(HashMap<String, Integer> stormMap, int maxWindSpeed) {
        if (stormMap.containsKey(stormName)) {
            Integer oldMaxWindSpeed = stormMap.get(stormName);
            if (maxWindSpeed > oldMaxWindSpeed) {
                stormMap.put(stormName, maxWindSpeed);
            }
        } else {
            stormMap.put(stormName, maxWindSpeed);
        }
    }

    private void checkStormNameForEndingWithSuffix(String suffix, String line) {
        String[] array = line.split(",");
        stormName = array[1].trim();
        if (stormName.endsWith(suffix)) {
            stormFilter = true;
        } else {
            stormFilter = false;
        }
    }

    private URL getUrl(String targetURL) {
        URL url = null;
        try {
            url = new URL(targetURL);
        } catch (MalformedURLException e) {
            log.error(e.getStackTrace());
        }
        return url;
    }

}
