package com.example;

import com.iogogogo.supervisord.annotation.EnableSupervisordConfiguration;
import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.domain.SupervisordProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@EnableSupervisordConfiguration
public class ExampleSupervisordApplication implements CommandLineRunner {

    @Autowired
    private Supervisord supervisord;

    public static void main(String[] args) {
        SpringApplication.run(ExampleSupervisordApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> list = supervisord.listMethods();
        list.forEach(System.out::println);
//
//        boolean b = supervisord.startProcess("", false);
//
//        supervisord.stopProcess("",false);
//
//        supervisord.getProcessInfo("");

        List<SupervisordProcess> allProcessInfo = supervisord.getAllProcessInfo();

        allProcessInfo.forEach(System.out::println);
    }
}
