package com.sulabh.forexdata.service;

import com.sulabh.forexdata.model.ForexExchangeRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ApiFetchService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApiFetchService.class);

    RestTemplate restTemplate = new RestTemplate();

    public ForexExchangeRate fetchDataFromApi(String API) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);

        ResponseEntity<ForexExchangeRate> result =
                restTemplate.exchange(API, HttpMethod.GET,entity, ForexExchangeRate.class);

        LOGGER.info("data extracted from api : {}",result.getBody().toString());

        return result.getBody();

    }
}
