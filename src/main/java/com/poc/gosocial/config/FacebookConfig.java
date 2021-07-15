package com.poc.gosocial.config;

import com.poc.gosocial.service.impl.FacebookServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacebookConfig {

    @Value("${facebook.page.access.token}")
    private String pageAccessToken;

    @Bean
    public FacebookServiceImpl facebook(){
        return new FacebookServiceImpl(pageAccessToken);
    }
}
