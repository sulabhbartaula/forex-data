package com.sulabh.forexdata;

import com.sulabh.forexdata.service.CreateFileService;
import com.sulabh.forexdata.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ForexDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForexDataApplication.class, args);

    }
}
