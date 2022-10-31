package by.dmitry.shelepen.task3.service;

import by.dmitry.shelepen.task3.converter.ConverterReactorOutput;
import by.dmitry.shelepen.task3.dto.ReactorOutputDto;
import by.dmitry.shelepen.task3.model.ReactorOutput;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class O3ProductionDownloader {

    @Autowired
    ConverterReactorOutput converter;

    public static final String OKG_DOMAIN = "https://www.okg.se";
    public static final String OKG_REACTOR_OUTPUT = "/.netlify/functions/getReactorOutput";
    public static final String CHARSET_NAME = "UTF-8";

    public ReactorOutput getValueAndTime() {
        ReactorOutputDto reactorOutputDto = getReactorOutputDto();
        return converter.convertReactorOutputDtoToReactorOutput(reactorOutputDto);
    }

    public abstract ReactorOutputDto getReactorOutputDto();


}
