package com.sulabh.forexdata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class CreateFileService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreateFileService.class);

    String path = "b:\\forex\\";

    public String email;

    public void createFile(String fileContent, String fileName){

        File file = new File(path + fileName);

        if (!file.exists()){
            try {
                if (file.createNewFile()){
                    System.out.println("New File Created");
                    LOGGER.info("New File Created : {} ",file.getName());
                }else{
                    LOGGER.error("Duplicate file exists. Not able to create file.");
                }

                //write to file
                try (FileWriter writer = new FileWriter(file)){
                    writer.write(fileContent);
                    LOGGER.info("Content added to file : \n" +
                            " {} ",fileContent);
                }
            } catch (IOException e) {
                LOGGER.error("Exception Occurred : {}",e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
