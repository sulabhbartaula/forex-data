package com.sulabh.forexdata.controller;

import com.sulabh.forexdata.model.ForexExchangeRate;
import com.sulabh.forexdata.service.ApiFetchService;
import com.sulabh.forexdata.service.CreateFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    String fileContent;

    public final String API_AUDUSD =
            "https://eodhistoricaldata.com/api/real-time/AUDUSD.FOREX?fmt=json&api_token=60dd2b06a55d03.18616562";

    public final String API_AUDNZD =
            "https://eodhistoricaldata.com/api/real-time/AUDNZD.FOREX?fmt=json&api_token=60dd2b06a55d03.18616562";

    public final String API_AUDHKD =
            "https://eodhistoricaldata.com/api/real-time/AUDHKD.FOREX?fmt=json&api_token=60dd2b06a55d03.18616562";

    public final String API_AUDKRW =
            "https://eodhistoricaldata.com/api/real-time/AUDKRW.FOREX?fmt=json&api_token=60dd2b06a55d03.18616562";

    public final String API_AUDJPY =
            "https://eodhistoricaldata.com/api/real-time/AUDJPY.FOREX?fmt=json&api_token=60dd2b06a55d03.18616562";

    @Autowired
    ApiFetchService apiFetchService;

    @Autowired
    CreateFileService createFileService;

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/start")
    public String startSubscription(Model model) throws Exception {
        ForexExchangeRate forexExchangeRate = apiFetchService.fetchDataFromApi(API_AUDUSD);
        fileContent = "\n"+forexExchangeRate.getCode().substring(0,6).concat(",").concat(forexExchangeRate.getOpen());

        ForexExchangeRate forexExchangeRateAudNzd = apiFetchService.fetchDataFromApi(API_AUDNZD);
        fileContent = fileContent +
                "\n"+forexExchangeRateAudNzd.getCode().substring(0,6).concat(",").concat(forexExchangeRateAudNzd.getOpen());

        ForexExchangeRate forexExchangeRateAudHkd = apiFetchService.fetchDataFromApi(API_AUDHKD);
        fileContent = fileContent +
                "\n"+forexExchangeRateAudHkd.getCode().substring(0,6).concat(",").concat(forexExchangeRateAudHkd.getOpen());

        ForexExchangeRate forexExchangeRateAudKrw = apiFetchService.fetchDataFromApi(API_AUDKRW);
        fileContent = fileContent +
                "\n"+forexExchangeRateAudKrw.getCode().substring(0,6).concat(",").concat(forexExchangeRateAudKrw.getOpen());

        ForexExchangeRate forexExchangeRateAudJpy = apiFetchService.fetchDataFromApi(API_AUDJPY);
        fileContent = fileContent +
                "\n"+forexExchangeRateAudJpy.getCode().substring(0,6).concat(",").concat(forexExchangeRateAudJpy.getOpen());

        createFileService.createFile(fileContent);
        return "index";
    }
}
