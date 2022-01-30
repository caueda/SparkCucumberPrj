package com.example.gft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.gft.config.Config;

@SpringBootApplication
@EnableConfigurationProperties(Config.class)
public class GftApplication {

    public static void main(String[] args) {
        SpringApplication.run(GftApplication.class, args);
    }

}
