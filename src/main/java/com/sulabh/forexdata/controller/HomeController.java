package com.sulabh.forexdata.controller;

import com.sulabh.forexdata.model.ForexExchangeRate;
import com.sulabh.forexdata.service.ApiFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ApiFetchService apiFetchService;

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/start")
    public String startSubscription(Model model) throws Exception {
        ForexExchangeRate forexExchangeRate = apiFetchService.fetchDataFromApi();
        model.addAttribute("code",forexExchangeRate.getCode());
        model.addAttribute("open",forexExchangeRate.getOpen());
        return "index";
    }
}
