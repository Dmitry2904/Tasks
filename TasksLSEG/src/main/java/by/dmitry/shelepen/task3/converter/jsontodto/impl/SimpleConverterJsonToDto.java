package by.dmitry.shelepen.task3.converter.jsontodto.impl;

import by.dmitry.shelepen.task3.converter.jsontodto.ConverterJsonToDto;
import by.dmitry.shelepen.task3.dto.ReactorOutputDto;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SimpleConverterJsonToDto implements ConverterJsonToDto {
    @Override
    public ReactorOutputDto convert(JSONObject jsonObject) {
        String output = jsonObject.get("output").toString();
        String time = jsonObject.get("time").toString();
        return new ReactorOutputDto(output, time);
    }
}
