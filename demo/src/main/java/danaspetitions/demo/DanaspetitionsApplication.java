package com.dana.danaspetitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DanaspetitionsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DanaspetitionsApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DanaspetitionsApplication.class);
    }
}