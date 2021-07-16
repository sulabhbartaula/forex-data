package com.sulabh.forexdata.model;

import org.springframework.stereotype.Component;

@Component
public class Api {

    public String baseApi;
    public String apiToken;
    public String currencyPair;

    public Api() {
    }

    public Api(String baseApi, String apiToken, String currencyPair) {
        this.baseApi = baseApi;
        this.apiToken = apiToken;
        this.currencyPair = currencyPair;
    }

    public String getBaseApi() {
        return baseApi;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

     //"https://eodhistoricaldata.com/api/real-time/AUDUSD.FOREX?fmt=json&api_token=60dd2b06a55d03.18616562";
    public String generateFinalApi(){
        String finalApi = baseApi + currencyPair + ".FOREX?fmt=json&api_token=" + apiToken;
        return finalApi;
    }
}
