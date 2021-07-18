package com.sulabh.forexdata.controller;

import com.sulabh.forexdata.service.SubscribeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/")
public class HomeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    SubscribeService subscribeService;

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/start")
    public String startSubscription(@RequestParam("email")String email,
            Model model){
        LOGGER.info("Subscriber request received for email : {}",email);

        subscribeService.setToEmail(email);

        try {
            subscribeService.scheduleSendingUpdates();
        } catch (ExecutionException e) {
            LOGGER.error("Exception occured : {}",e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }
}
