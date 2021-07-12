# Supervisord process manager for spring boot starter

Reference：

- https://github.com/satifanie/supervisord4j
- https://github.com/gturri/aXMLRPC
- https://gitee.com/felord/sms-spring-boot

Usage：

@see ext/supervisord4j

- pom

```xml

<dependency>
    <groupId>com.iogogogo.supervisord</groupId>
    <artifactId>supervisord-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

- configuration

```java
package com.example;

import com.iogogogo.supervisord.annotation.EnableSupervisord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@EnableSupervisord
@SpringBootApplication
public class ExampleSupervisordApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleSupervisordApplication.class, args);
    }
}

```

- subscribe event

```java

package com.example.listener;

import com.iogogogo.supervisord.event.SupervisordEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * // https://www.cnblogs.com/doudouxiaoye/p/5688629.html
 * Type superClz = source.getClass().getGenericSuperclass();
 * Type type = ((ParameterizedType) superClz).getActualTypeArguments()[0];
 *
 * <p>
 * Created by tao.zeng on 2021/6/24.
 */
@Slf4j
@Component
public class SupervisordEventListener implements ApplicationListener<SupervisordEvent> {

    @Override
    public void onApplicationEvent(SupervisordEvent supervisordEvent) {
        log.info("supervisordEvent: {}", supervisordEvent);
    }
}


```
