//package com.iogogogo.supervisord.base;
//
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//
//import javax.persistence.EntityManager;
//
///**
// * Created by tao.zeng on 2021/7/1.
// */
//public class BaseJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {
//
//    protected EntityManager em;
//
//    public BaseJpaRepository(Class<T> domainClass, EntityManager em) {
//        super(domainClass, em);
//        this.em = em;
//    }
//}
