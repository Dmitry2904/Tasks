package by.dmitry.shelepen.task4;

import by.dmitry.shelepen.task4.service.StormService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Set;

/**
 * Task 4.
 * Get the text file. Create an application which outputs storm name and maximum sustained
 * wind-speed in knots for each of the storms after 2015 with name ending with A (maximum
 * value from a year)
 * <p>
 * • Format description: <a href="http://www.nhc.noaa.gov/data/hurdat/hurdat2-format-nencpac.pdf">
 * http://www.nhc.noaa.gov/data/hurdat/hurdat2-format-nencpac.pdf</a>
 * <p>
 * • Hurricane tracks file: <a href="http://www.nhc.noaa.gov/data/hurdat/hurdat2-nepac-1949-2016-041317.txt">
 * http://www.nhc.noaa.gov/data/hurdat/hurdat2-nepac-1949-2016-041317.txt</a>
 */
public class Application4 {
    private static final Logger log = LogManager.getLogger(Application4.class);

    public static void main(String[] args) {

        String targetURL = "https://www.nhc.noaa.gov/data/hurdat/hurdat2-nepac-1949-2016-041317.txt";

        Map<String, Integer> stormMap = new StormService()
                .getStormsWithMaxSustainWindAfterYearAndEndsWith(targetURL, 2015, "A");

        new Application4().printStormMapToConsole(stormMap);

    }

    private void printStormMapToConsole(Map<String, Integer> stormMap) {
        log.info("-----------------------------------------");
        log.info("StormName\t\t| max sustain wind speed");
        log.info("-----------------------------------------");
        Set<Map.Entry<String, Integer>> entries = stormMap.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String output = String.format("%s\t\t\t|\t%s", entry.getKey(), entry.getValue());
            log.info(output);
        }
    }
}
