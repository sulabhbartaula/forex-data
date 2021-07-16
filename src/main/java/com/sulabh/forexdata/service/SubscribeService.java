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

    private String fileContent;
    private Api api;
    private String baseApi = "https://eodhistoricaldata.com/api/real-time/";
    private String token = "60dd2b06a55d03.18616562";
    private String toEmail;

    @Autowired
    ApiFetchService apiFetchService;

    @Autowired
    CreateFileService createFileService;

    @Autowired
    CurrencyPair currencyPair;

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "0 0 8,12,16,21 ? * *", zone = "GMT+10")
    public void scheduleSendingUpdates() throws ExecutionException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'_'HHmm");
        // a filename of obsval_YYYYMMDD_HHMM.csv e.g. obsval_20191015_0800.csv
        String fileName = "obsval_"+ LocalDateTime.now().format(formatter) + ".csv";

        for(String cp : currencyPair.getCurrencyPair()){
            api = new Api(baseApi,token,cp);
            String localApi = api.generateFinalApi();
            ForexExchangeRate forexExchangeRate = apiFetchService.fetchDataFromApi(localApi);
            fileContent =
                    fileContent + "\n"+ forexExchangeRate.getCode().substring(0,6).concat(",").concat(forexExchangeRate.getOpen());
        }

        //fileContent = "\nAUDUSD,.69";

        createFileService.createFile(fileContent,fileName);

        emailService.sendEmail(toEmail,fileName);
    }

    public void setBaseApi(String baseApi) {
        this.baseApi = baseApi;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
        this.createFileService.email = toEmail;
    }


}
