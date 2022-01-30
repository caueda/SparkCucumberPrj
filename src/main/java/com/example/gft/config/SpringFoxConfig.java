package com.example.gft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SpringFoxConfig {
    private static final Contact DEFAULT_CONTACT = new Contact("Carlos R. Ueda", "www.devueda.com", "carlos.ueda@gmail.com");
    private static final ApiInfo DEFAULT_API_INFO =
            new ApiInfo("GFT Api Documentation",
                    "GFT Api Documentation",
                    "1.0", "urn:tos",
                    DEFAULT_CONTACT,
                    "Apache 2.0",
                    "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
