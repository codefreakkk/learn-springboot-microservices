package com.userservice.userservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {

                String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
                String jwtToken = "Bearer " + token;
                requestTemplate.header("Authorization", jwtToken);
                System.out.println("Interceptor token: " + token);
            }
        };
    }
}
