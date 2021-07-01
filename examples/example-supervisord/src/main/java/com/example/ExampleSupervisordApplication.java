package com.example;

import com.iogogogo.supervisord.annotation.EnableSupervisord;
import com.iogogogo.supervisord.core.Supervisord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@EnableSupervisord
@SpringBootApplication
public class ExampleSupervisordApplication implements CommandLineRunner {

    @Autowired
    private Supervisord supervisord;

    public static void main(String[] args) {
        SpringApplication.run(ExampleSupervisordApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> listMethods = supervisord.listMethods();
        listMethods.forEach(x -> log.info("method name: {}", x));

    }
}
