package com.example.apibot.services;

import com.example.apibot.bot.Stock_Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apibot.repository.MyRepo;

@Service
public class Myservice {
    public Myservice() {
    }

    private MyRepo myRepo;

    @Autowired
    public Myservice(MyRepo myRepo) {
        this.myRepo = myRepo;
    }

    public void inserttoDb(Stock_Bot data) {
        myRepo.savetoDb(data);

    }
}
