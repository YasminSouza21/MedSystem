package com.yasmin.projects.medsystem.api.domain.patient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GetCepDataByApi {

    private final RestTemplate restTemplate;

    public GetCepDataByApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CepDataDTO getData(String cep){
        return restTemplate.getForObject("https://viacep.com.br/ws/"+ cep + "/json/", CepDataDTO.class);
    }
}
