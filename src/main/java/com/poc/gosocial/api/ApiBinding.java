package com.poc.gosocial.api;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public abstract class ApiBinding {

    protected RestTemplate restTemplate;

    public ApiBinding(String bearerToken){
        this.restTemplate = new RestTemplate();
        if(bearerToken != null){
            this.restTemplate.getInterceptors().add(getBearerTokenInterceptor(bearerToken));
        }
        else {
            this.restTemplate.getInterceptors().add(getNoTokenInterceptor());
        }
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String bearerToken){
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
                httpRequest.getHeaders().add("Authorization", "Bearer " + bearerToken);
                return clientHttpRequestExecution.execute(httpRequest,bytes);
            }
        };
    }

    private ClientHttpRequestInterceptor getNoTokenInterceptor(){
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
                throw new IllegalStateException("Can't access the API without an access token");
            }
        };
    }
}
