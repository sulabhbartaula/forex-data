package com.sulabh.forexdata.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class CreateFileService {

    String path = "b:\\forex\\";

    public String email;

    public void createFile(String fileContent, String fileName){

        File file = new File(path + fileName);

        if (!file.exists()){
            try {
                if (file.createNewFile()){
                    System.out.println("New File Created");
                    System.out.println("File created at : "+ LocalDateTime.now() + "for" + email);
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
}
