package com.iogogogo.supervisord.configuration;

import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.properties.SupervisordProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tao.zeng on 2021/6/22.
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "supervisord", name = "url")
public class SupervisordConfiguration {

    private final SupervisordProperties supervisordProperties;

    private final ApplicationContext applicationContext;

    public SupervisordConfiguration(ApplicationContext applicationContext, SupervisordProperties supervisordProperties) {
        this.applicationContext = applicationContext;
        this.supervisordProperties = supervisordProperties;
    }

    @Bean
    public Supervisord supervisord() {
        return Supervisord.connect(supervisordProperties.getUrl())
                .applicationContext(applicationContext)
                .auth(supervisordProperties.getUsername(), supervisordProperties.getPassword());
    }
}
