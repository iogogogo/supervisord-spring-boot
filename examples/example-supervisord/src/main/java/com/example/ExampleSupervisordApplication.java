package com.example;

import com.iogogogo.supervisord.annotation.EnableSupervisord;
import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.domain.SupervisordProcess;
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
        List<String> list = supervisord.listMethods();
        list.forEach(System.out::println);

        String procName = "logstash_test";

        boolean ret = supervisord.startProcess(procName, false);
        System.out.println(ret);

        ret = supervisord.stopProcess(procName, false);
        System.out.println(ret);

        SupervisordProcess processInfo = supervisord.getProcessInfo(procName);
        System.out.println(processInfo);

        List<SupervisordProcess> allProcessInfo = supervisord.getAllProcessInfo();
        allProcessInfo.forEach(System.out::println);
    }
}
