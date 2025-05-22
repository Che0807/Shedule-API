package com.example.sheduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SheduleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SheduleApiApplication.class, args);
    }

}
