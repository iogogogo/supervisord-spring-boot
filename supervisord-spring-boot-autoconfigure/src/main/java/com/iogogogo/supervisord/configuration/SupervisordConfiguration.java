package com.iogogogo.supervisord.configuration;

import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.properties.SupervisordProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tao.zeng on 2021/6/22.
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = SupervisordProperties.SUPERVISORD_PREFIX, name = "url")
public class SupervisordConfiguration {

    private final SupervisordProperties supervisordProperties;

    /**
     * Instantiates a new Supervisord configuration.
     *
     * @param supervisordProperties the supervisord properties
     */
    public SupervisordConfiguration(SupervisordProperties supervisordProperties) {
        this.supervisordProperties = supervisordProperties;
    }

    /**
     * Supervisord supervisord.
     *
     * @return the supervisord
     */
    @Bean
    public Supervisord supervisord() {
        return Supervisord
                .connect(supervisordProperties.getUrl())
                .auth(supervisordProperties.getUsername(), supervisordProperties.getPassword());
    }
}
