package com.example.apibot;

import com.example.apibot.bot.LondonStockExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ApibotApplication {
    LondonStockExchange londonStockExchange;

    @Autowired
    ApibotApplication(LondonStockExchange londonStockExchange) {
        this.londonStockExchange = londonStockExchange;
        try {
            londonStockExchange.showApiData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        SpringApplication.run(ApibotApplication.class, args);
    }

}
