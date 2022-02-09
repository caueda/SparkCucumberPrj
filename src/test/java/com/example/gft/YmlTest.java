package com.example.gft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.example.gft.config.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.vavr.API;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class YmlTest {
    @Autowired
    public ResourceLoader resourceLoader;
    @Autowired
    public Config config;

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

    @Test
    public void testExternalMappingResource() {
        String EXPECTED = "c:/temp";
        String actual = config.getExternalMapping().get("local");
        log.info("Actual value {}", actual);
        assertEquals(EXPECTED, actual);
    }
}
