package com.sulabh.forexdata.service;

import com.sulabh.forexdata.model.ForexExchangeRate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ApiFetchService {

    RestTemplate restTemplate = new RestTemplate();

    public ForexExchangeRate fetchDataFromApi(String API) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);

        ResponseEntity<ForexExchangeRate> result = restTemplate.exchange(API, HttpMethod.GET,entity, ForexExchangeRate.class);

        return result.getBody();

    }
}
