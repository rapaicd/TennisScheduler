package com.tennis.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

        return builder.routes()
                .route(p->p.path("/get").uri("https://httpbin.org"))
                .route(p->p.path("/persons/**").uri("lb://user"))
                .route(p->p.path("/timeslots/**").uri("lb://timeslot"))
                .route(p->p.path("/tennis-courts/**").uri("lb://timeslot"))
                .route(p->p.path("/auth/**").uri("lb://authorization-server"))
                .build();
    }
}
