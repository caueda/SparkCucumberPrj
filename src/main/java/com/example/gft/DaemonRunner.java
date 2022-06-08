package com.example.gft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DaemonRunner {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DaemonRunner.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = application.run(args);
        SpringApplication.exit(context, () -> 0);
    }
}
