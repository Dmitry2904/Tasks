package by.dmitry.shelepen.task1;

import by.dmitry.shelepen.task1.service.url.parser.UrlParserService;
import by.dmitry.shelepen.task1.service.url.parser.impl.UrlParserHttpURLConnectionImpl;
import by.dmitry.shelepen.task1.util.FileWriterUtil;

/**
 * Write an application that downloads only table Statistics Electricity Imbalance from
 * <a href="https://www.ote-cr.cz/en/statistics/electricity-imbalances">
 * https://www.ote-cr.cz/en/statistics/electricity-imbalances</a>
 * and store it as a HTML file.
 */
public class Application1 {

    public static final String URL = "https://www.ote-cr.cz/en/statistics/electricity-imbalances";

    public static final String FILE_NAME = "result.html";

    public static void main(String[] args) {
        UrlParserService urlParserService = new UrlParserHttpURLConnectionImpl();
//        UrlParserService urlParserService = new UrlParserJsoupImpl();

        String table = urlParserService.downloadTagTableByUrl(URL);
        new FileWriterUtil().writeDataToTargetHtmlFile(table, FILE_NAME);
    }
}