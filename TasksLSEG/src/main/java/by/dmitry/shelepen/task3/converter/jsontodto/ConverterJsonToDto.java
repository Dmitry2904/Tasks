package by.dmitry.shelepen.task3.converter.jsontodto;

import by.dmitry.shelepen.task3.dto.ReactorOutputDto;
import org.json.JSONObject;

public interface ConverterJsonToDto {
    ReactorOutputDto convert(JSONObject jsonObject);
}
