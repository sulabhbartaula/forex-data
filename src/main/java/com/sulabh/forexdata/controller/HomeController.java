package com.sulabh.forexdata.controller;

import com.sulabh.forexdata.model.Api;
import com.sulabh.forexdata.model.CurrencyPair;
import com.sulabh.forexdata.model.ForexExchangeRate;
import com.sulabh.forexdata.service.ApiFetchService;
import com.sulabh.forexdata.service.CreateFileService;
import com.sulabh.forexdata.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/start")
    @Scheduled(cron = "0 0 19,20 ?  * *")
    public String startSubscription(Model model) throws Exception {

        for(String cp : currencyPair.getCurrencyPair()){
            api = new Api(baseApi,token,cp);
            String localApi = api.generateFinalApi();
            ForexExchangeRate forexExchangeRate = apiFetchService.fetchDataFromApi(localApi);
            fileContent =
                    fileContent + "\n"+ forexExchangeRate.getCode().substring(0,6).concat(",").concat(forexExchangeRate.getOpen());
        }

        createFileService.createFile(fileContent,fileName);

        emailService.sendEmail("sulabhbartaula@gmail.com",fileName);

        return "index";
    }
}
