package com.seniorjob.seniorjobserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SeniorJopServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeniorJopServerApplication.class, args);
    }
}
