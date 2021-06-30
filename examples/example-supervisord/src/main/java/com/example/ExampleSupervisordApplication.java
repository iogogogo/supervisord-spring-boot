package com.example;

import com.iogogogo.supervisord.annotation.EnableSupervisord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableSupervisord
@SpringBootApplication
public class ExampleSupervisordApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleSupervisordApplication.class, args);
    }
}
