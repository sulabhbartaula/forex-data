package com.sulabh.forexdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForexExchangeRate {

    private String code; //currency pair code
    private String open; //opening price

    public ForexExchangeRate() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "ForexExchangeRate{" +
                "code='" + code + '\'' +
                ", open='" + open + '\'' +
                '}';
    }
}
