package by.dmitry.shelepen.task3.converter;

import by.dmitry.shelepen.task3.dto.ReactorOutputDto;
import by.dmitry.shelepen.task3.model.ReactorOutput;
import by.dmitry.shelepen.task3.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ConverterReactorOutput {

    @Autowired
    DateTimeUtil dateTimeUtil;

    @Autowired
    ReactorOutput reactorOutput;

    public ReactorOutput convertReactorOutputDtoToReactorOutput(ReactorOutputDto dto) {
        String output = dto.getOutput();
        String time = dto.getTime();

        int value = Integer.parseInt(output);
        reactorOutput.setValue(value);

        time = time.replace(" ", "");
        time = dateTimeUtil.checkAndCorrectTimeZone(time);
        OffsetDateTime date = OffsetDateTime.parse(time);
        reactorOutput.setTime(date);

        return reactorOutput;
    }
}
