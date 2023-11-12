package com.tennis.authorizationserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
public class CORSCustomizer {
    private static String frontend;

    @Value("${api.frontend_url}")
    public void setFrontend(String frontend){
        this.frontend = frontend;
    }

    public void corsCustomizer(HttpSecurity http) throws Exception{
        http.cors(c->{
            CorsConfigurationSource source = s->{
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowCredentials(true);
                cc.setAllowedOrigins(List.of(frontend));
                cc.setAllowedHeaders(List.of("*"));
                cc.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                return cc;
            };
            c.configurationSource(source);
        });
    }

}
