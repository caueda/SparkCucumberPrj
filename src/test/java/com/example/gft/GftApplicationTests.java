package com.example.gft;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gft.config.Config;

@SpringBootTest
class GftApplicationTests {

    @Autowired
    Config config;

    @Test
    void contextLoads() {
        System.out.println(config.getOs());
    }

}
