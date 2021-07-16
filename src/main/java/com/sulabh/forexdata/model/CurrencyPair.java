package com.sulabh.forexdata.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CurrencyPair {

    ArrayList<String> currencyPair = new ArrayList<>();

    public CurrencyPair() {
        currencyPair.add("AUDUSD");
        currencyPair.add("AUDNZD");
        currencyPair.add("AUDHKD");
        currencyPair.add("AUDKRW");
        currencyPair.add("AUDJPY");
    }

    public ArrayList<String> getCurrencyPair() {
        return currencyPair;
    }
}
