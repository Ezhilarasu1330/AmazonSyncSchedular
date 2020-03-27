package com.example.amazonsync.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertiesUtil {

    @Autowired
    private Environment environment;

    public void printProperties(){
        System.out.println(environment.getProperty("amazon.sellerid"));
    }
}