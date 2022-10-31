package by.dmitry.shelepen.task1.service.url.parser.impl;

import by.dmitry.shelepen.task1.converter.IoConverter;
import by.dmitry.shelepen.task1.service.url.parser.UrlParserService;
import by.dmitry.shelepen.task1.util.StringParserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlParserHttpURLConnectionImpl implements UrlParserService {
    private static final Logger log = LogManager.getLogger(UrlParserHttpURLConnectionImpl.class);

    public static final String PREFIX = "<table";
    public static final String SUFFIX = "</table>";

    @Override
    public String downloadTagTableByUrl(String url) {
        return writeValueFromUrlBetweenPrefixAndSuffixToFile(url, PREFIX, SUFFIX);
    }

    public String writeValueFromUrlBetweenPrefixAndSuffixToFile(String url, String prefix, String suffix) {
        String allHtmlDataInString = getAllHtmlFromUrl(url);
        return new StringParserUtil().parseValueBetweenPrefixAndSuffix(allHtmlDataInString, prefix, suffix);
    }

    public String getAllHtmlFromUrl(String url) {
        String allHtmlData = "";
        try {
            URL address = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();
            optionalSettings(connection);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            allHtmlData = new IoConverter().convertBufferReaderToString(in);
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
        return allHtmlData;
    }

    private void optionalSettings(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod("GET");
        connection.setRequestProperty("accept-language", "en");
        int httpStatus = connection.getResponseCode();
        if (httpStatus != HttpURLConnection.HTTP_OK) {
            throw new IOException("Server response with error, code: " + httpStatus);
        }
    }


}