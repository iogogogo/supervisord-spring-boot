package com.iogogogo.supervisord;

import com.iogogogo.supervisord.api.SupervisordRpcApi;
import com.iogogogo.supervisord.aspect.SupervisordAspect;
import com.iogogogo.supervisord.configuration.OkHttpConfiguration;
import com.iogogogo.supervisord.configuration.SupervisordConfiguration;
import com.iogogogo.supervisord.properties.SupervisordProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by tao.zeng on 2021/6/21.
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SupervisordProperties.class)
// SQLiteConfiguration.class,
@Import({SupervisordConfiguration.class, OkHttpConfiguration.class, SupervisordAspect.class, SupervisordRpcApi.class})
public class SupervisordAutoConfiguration {
}
