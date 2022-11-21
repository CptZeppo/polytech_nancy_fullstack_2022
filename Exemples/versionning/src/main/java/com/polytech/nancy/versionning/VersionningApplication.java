package com.polytech.nancy.versionning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class VersionningApplication {

    public static void main(String[] args) {
        SpringApplication.run(VersionningApplication.class, args);
    }

}
