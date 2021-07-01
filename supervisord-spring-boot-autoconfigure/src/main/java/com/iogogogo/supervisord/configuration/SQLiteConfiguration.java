//package com.iogogogo.supervisord.configuration;
//
//import com.iogogogo.supervisord.repository.SupervisorRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//
///**
// * Created by tao.zeng on 2021/7/1.
// */
//@Slf4j
//@EntityScan(basePackages = "com.iogogogo.supervisord.domain.pojo")
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
//public class SQLiteConfiguration {
//
//    private final EntityManager entityManager;
//
//    public SQLiteConfiguration(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Bean
//    @ConditionalOnBean(value = DataSource.class)
//    public SupervisorRepository supervisorRepository() {
//        return new SupervisorRepository(entityManager);
//    }
//}
