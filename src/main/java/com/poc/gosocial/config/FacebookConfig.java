package com.poc.gosocial.config;

import com.poc.gosocial.api.facebook.Facebook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacebookConfig {

    @Value("${facebook.page.access.token}")
    private String pageAccessToken;

    @Bean
    public Facebook facebook(){
        return new Facebook(pageAccessToken);
    }
}
