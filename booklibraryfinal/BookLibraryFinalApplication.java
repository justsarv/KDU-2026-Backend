package com.example.booklibraryfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BookLibraryFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookLibraryFinalApplication.class, args);
    }

}
