package com.iogogogo.supervisord4j.configuration;

import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by tao.zeng on 2021/7/11.
 */
@Configuration
public class GitConfiguration {

    @Bean
    public GitProperties gitProperties() throws IOException {
        Properties properties = new Properties();
        Resource resource = new ClassPathResource("git.properties");
        properties.load(resource.getInputStream());
        return new GitProperties(properties);
    }
}
