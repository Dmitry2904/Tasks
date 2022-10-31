package by.dmitry.shelepen.task2.service;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class XlsDownloader {

    private static final Logger log = LogManager.getLogger(XlsDownloader.class);


    public File downloadXlsFile() {
        String url = "https://www.ote-cr.cz/en/statistics/electricity-imbalances";
        String link = getLinkFromUrl(url);
        String fileName = getFileNameFromDownloadedLink(link);
        return downloadXlsByLink(link, fileName);
    }

    public String getLinkFromUrl(String url) {
        String link = "";
        try {
            Document doc = Jsoup.connect(url).get();
            Element pElement = doc.select("p.report_attachment_links").first();
            if (pElement != null) {
                Elements a = pElement.getElementsByTag("a");
                if (a != null) {
                    link = a.attr("href");
                }
            }
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return url.concat(link);
    }


    private String getFileNameFromDownloadedLink(String link) {
        if (link != null) {
            String[] split = link.split("/");
            for (String part : split) {
                if (part.endsWith(".xls")) {
                    return part;
                }
            }
        }
        return "";
    }


    public File downloadXlsByLink(String urlStr, String file) {
        URL urlSource = null;
        File xlsFile = new File(file);
        try {
            urlSource = new URL(urlStr);
            FileUtils.copyURLToFile(urlSource, xlsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xlsFile;
    }

}
