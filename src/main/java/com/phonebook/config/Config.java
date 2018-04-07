package com.phonebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vadim on 14.10.2017.
 */
@Configuration
public class Config {
    @Bean(name="urlService")
    public UrlService urlService(){
        return new UrlService() {
            @Override
            public String getApplicationUrl() {
                return "domain";
            }
        };
    }
}

interface UrlService{
    String getApplicationUrl();
}