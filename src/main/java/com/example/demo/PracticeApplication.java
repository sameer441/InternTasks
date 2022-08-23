package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PracticeApplication.class, args);

        ReadDataFromExcel DataFromExcel = new ReadDataFromExcel();
        DataFromExcel.getDataFromExcel();

    }

}
