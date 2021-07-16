package com.sulabh.forexdata.controller;

import com.sulabh.forexdata.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/")
public class HomeController {

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
