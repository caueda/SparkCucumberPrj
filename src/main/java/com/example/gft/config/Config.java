package com.example.gft.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ImportResource
@ConfigurationProperties(prefix = "taco")
public class Config {
    private String appName;
    private String cpus;
    private String os;
    public Map<String, String> externalMapping;
}
