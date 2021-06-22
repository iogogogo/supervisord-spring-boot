package com.iogogogo.supervisord;

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
@Import({SupervisordConfiguration.class})
public class SupervisordAutoConfiguration {
}
