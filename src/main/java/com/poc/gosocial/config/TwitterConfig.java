package com.poc.gosocial.config;

import com.poc.gosocial.api.twitter.Twitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterConfig {

    @Value("${twitter.bearer.token}")
    private String twitterBearerToken;

    @Bean
    public Twitter twitter(){
        return new Twitter(twitterBearerToken);
    }
}
