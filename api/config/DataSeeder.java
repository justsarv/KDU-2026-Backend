package com.example.api.config;

import com.example.api.auth.UserRegistrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedUsers(UserRegistrationService reg) {
        return args -> {
            reg.register("sarvesh", "password123", "sarvesh@test.com", List.of("ROLE_BASIC"));
            reg.register("admin", "admin123", "admin@test.com", List.of("ROLE_ADMIN"));
        };
    }
}
