package com.example.apibot;

import bot.LondonStockExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.jar.JarOutputStream;

@SpringBootApplication
public class ApibotApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ApibotApplication.class, args);
        LondonStockExchange Post_Response = new LondonStockExchange();
        Post_Response.showApiData();
    }

}
