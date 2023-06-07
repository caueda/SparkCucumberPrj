package com.example.gft;

import com.example.gft.config.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
            var file = new PathMatchingResourcePatternResolver(resourceLoader)
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

    @Test
    public void optionalTest() {
        Optional<String> opt = Optional.ofNullable("success");
        var classpath = opt.map(p -> p + "!!!!")
                .orElse("There was nothing");
        System.out.println(classpath);
    }
}
