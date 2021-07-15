package com.sulabh.forexdata;

import com.sulabh.forexdata.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForexDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForexDataApplication.class, args);

        EmailService emailService = new EmailService();

        for(int i = 0; i < 20; i++ ){
            emailService.sendEmail();
            System.out.printf("\nEmail Sent : "+ (i+1));
        }

    }

}
