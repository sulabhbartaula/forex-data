package com.sulabh.forexdata.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class CreateFileService {

    String fileName = "myfile.csv";

    public void createFile(String fileContent){

        File file = new File("G:\\data\\prajit.csv");
        //String AUSUSD = "\nAUSUSD".concat(",.7485");

        try {
            if (file.createNewFile()){
                System.out.println("New File Created");
            }else{
                System.out.println("Some problem");
            }

            //write to file
            try (FileWriter writer = new FileWriter(file)){
                writer.write("FOREX,VALUE");
                writer.append(fileContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
