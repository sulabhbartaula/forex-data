package com.sulabh.forexdata.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class CreateFileService {


    public void createFile(String fileContent, String fileName){

        File file = new File("b:\\forex\\"+ fileName);

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
