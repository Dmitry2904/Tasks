package by.dmitry.shelepen.task3.service;

import by.dmitry.shelepen.task3.converter.jsontodto.ConverterJsonToDto;
import by.dmitry.shelepen.task3.dto.ReactorOutputDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class O3ProductionInputStreamDownloader extends O3ProductionDownloader {

    private static final Logger log = LogManager.getLogger(O3ProductionInputStreamDownloader.class);

    @Autowired
    @Qualifier("simpleConverterJsonToDto")
//    @Qualifier("gsonConverterJsonToDto")
    ConverterJsonToDto converterJsonToDto;


    @Override
    public ReactorOutputDto getReactorOutputDto() {
        JSONObject jsonObject = readJsonFromUrl(OKG_DOMAIN + OKG_REACTOR_OUTPUT);
        return converterJsonToDto.convert(jsonObject);
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) {
        try (InputStream is = new URL(url).openStream();) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } catch (IOException e) {
            log.error(e.getStackTrace());
            return new JSONObject();
        }
    }
}
