package com.example.srbademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SrbaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrbaDemoApplication.class, args);
    }

}
