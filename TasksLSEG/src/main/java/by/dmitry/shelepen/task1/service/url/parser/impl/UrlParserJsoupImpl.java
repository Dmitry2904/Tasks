package by.dmitry.shelepen.task1.service.url.parser.impl;


import by.dmitry.shelepen.task1.service.url.parser.UrlParserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class UrlParserJsoupImpl implements UrlParserService {
    private static final Logger log = LogManager.getLogger(UrlParserJsoupImpl.class);

    @Override
    public String downloadTagTableByUrl(String url) {
        String table = "";
        try {
            Document doc = Jsoup.connect(url).get();
            Element tableElement = doc.select("table").first();
            if (tableElement != null) {
                table = tableElement.toString();
            }
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
        return table;
    }
}
