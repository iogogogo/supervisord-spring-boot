package com.iogogogo.supervisord.configuration;

import com.iogogogo.supervisord.properties.SupervisordProperties;
import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.core.rpc.SupervisordRpc;
import okhttp3.OkHttpClient;
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

    private final OkHttpClient httpClient;


    public SupervisordConfiguration(OkHttpClient httpClient, SupervisordProperties supervisordProperties) {
        this.supervisordProperties = supervisordProperties;
        this.httpClient = httpClient;
    }

    /**
     * Supervisord supervisord.
     *
     * @return the supervisord
     */
    @Bean
    public Supervisord supervisord() {
        return new SupervisordRpc(this.httpClient, supervisordProperties);
    }
}
