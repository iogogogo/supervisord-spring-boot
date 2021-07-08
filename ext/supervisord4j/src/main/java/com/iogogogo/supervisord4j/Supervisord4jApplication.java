package com.iogogogo.supervisord4j;

import com.iogogogo.supervisord.annotation.EnableSupervisord;
import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.exception.SupervisordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.io.IOException;
import java.util.List;

@Slf4j
@EnableOpenApi
@EnableSupervisord
@SpringBootApplication
public class Supervisord4jApplication implements CommandLineRunner {

    @Autowired
    private Supervisord supervisord;

    public static void main(String[] args) {
        SpringApplication.run(Supervisord4jApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // listMethod();

        log.info("Supervisord4jApplication start completed.");
    }

    private void listMethod() throws SupervisordException, IOException {
        List<String> listMethods = supervisord.listMethods();
        listMethods.forEach(x -> log.info("method name: {}", x));
    }
}
