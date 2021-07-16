package com.sulabh.forexdata.controller;

import com.sulabh.forexdata.model.Api;
import com.sulabh.forexdata.model.CurrencyPair;
import com.sulabh.forexdata.model.ForexExchangeRate;
import com.sulabh.forexdata.service.ApiFetchService;
import com.sulabh.forexdata.service.CreateFileService;
import com.sulabh.forexdata.service.EmailService;
import com.sulabh.forexdata.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/")
public class HomeController {

    String fileContent;
    Api api;
    String baseApi = "https://eodhistoricaldata.com/api/real-time/";
    String token = "60dd2b06a55d03.18616562";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'_'HHmm");

    // a filename of obsval_YYYYMMDD_HHMM.csv e.g. obsval_20191015_0800.csv
    String fileName = "obsval_"+ LocalDateTime.now().format(formatter) + ".csv";

    @Autowired
    ApiFetchService apiFetchService;

    @Autowired
    CreateFileService createFileService;

    @Autowired
    CurrencyPair currencyPair;

    @Autowired
    EmailService emailService;

    @Autowired
    SubscribeService subscribeService;

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/start")
    public String startSubscription(Model model){
        try {
            subscribeService.scheduleSendingUpdates();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
