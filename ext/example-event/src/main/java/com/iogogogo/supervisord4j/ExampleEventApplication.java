package com.iogogogo.supervisord4j;

import com.iogogogo.supervisord4j.event.EventObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api")
@SpringBootApplication
public class ExampleEventApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ExampleEventApplication.class, args);
    }

    @GetMapping("/event")
    public String event() {
        EventObject object = new EventObject("hello event.");
        object.setName("哈哈哈");
        object.setState(200);
        applicationContext.publishEvent(object);
        return "ok event.";
    }

    @EventListener
    public void doEvent(Object event) {
        if (event instanceof EventObject) {
            log.info("doEvent:{}", event);
        }

        if (event instanceof ServletRequestHandledEvent) {
            ServletRequestHandledEvent handledEvent = (ServletRequestHandledEvent) event;
            log.info("client:{}", handledEvent.getClientAddress());
            log.info("url:{}", handledEvent.getRequestUrl());
            log.info("method:{}", handledEvent.getMethod());
            log.info("time:{}", handledEvent.getProcessingTimeMillis());
            log.info("ts:{}", handledEvent.getTimestamp());
            Throwable throwable = handledEvent.getFailureCause();
            log.info("status:{}", Objects.isNull(throwable) ? "OK" : throwable);
            log.info("handledEvent:{}", handledEvent);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        applicationContext.publishEvent(new EventObject("spring boot start."));
    }
}
