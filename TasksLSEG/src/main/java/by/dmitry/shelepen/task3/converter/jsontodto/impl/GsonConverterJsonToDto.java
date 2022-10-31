package by.dmitry.shelepen.task3.converter.jsontodto.impl;

import by.dmitry.shelepen.task3.converter.jsontodto.ConverterJsonToDto;
import by.dmitry.shelepen.task3.dto.ReactorOutputDto;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GsonConverterJsonToDto implements ConverterJsonToDto {
    @Override
    public ReactorOutputDto convert(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), ReactorOutputDto.class);
    }


}
