package com.sulabh.forexdata.service;

import com.sulabh.forexdata.model.Api;
import com.sulabh.forexdata.model.CurrencyPair;
import com.sulabh.forexdata.model.ForexExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

@Service
public class SubscribeService {

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

    @Scheduled(cron = "0 0 19,20 ?  * *")
    public void scheduleSendingUpdates() throws ExecutionException {

        for(String cp : currencyPair.getCurrencyPair()){
            api = new Api(baseApi,token,cp);
            String localApi = api.generateFinalApi();
            ForexExchangeRate forexExchangeRate = apiFetchService.fetchDataFromApi(localApi);
            fileContent =
                    fileContent + "\n"+ forexExchangeRate.getCode().substring(0,6).concat(",").concat(forexExchangeRate.getOpen());
        }

        createFileService.createFile(fileContent,fileName);

        emailService.sendEmail("sulabhbartaula@gmail.com",fileName);
    }


}
