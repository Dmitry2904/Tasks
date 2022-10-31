package by.dmitry.shelepen.task3.service;

import by.dmitry.shelepen.task3.dto.ReactorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class O3ProductionRestTemplateDownloader extends O3ProductionDownloader {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ReactorOutputDto getReactorOutputDto() {
        return restTemplate.getForObject(OKG_DOMAIN + OKG_REACTOR_OUTPUT, ReactorOutputDto.class);
    }
}
