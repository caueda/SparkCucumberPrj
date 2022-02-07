package com.example.gft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.vavr.API;

@SpringBootTest
public class YmlTest {
    @Autowired
    public ResourceLoader resourceLoader;

    @Test
    public void ymlTest() {
         var source = API.unchecked(() -> {
             var file =new PathMatchingResourcePatternResolver(resourceLoader)
                    .getResource("classpath:queries.yml")
                    .getInputStream();
            return new ObjectMapper(new YAMLFactory()).readValue(file, Map.class);
        }).get();
        assertEquals("SELECT SYSDATE FROM DUAL", source.get("QUERY1"));
    }
}
