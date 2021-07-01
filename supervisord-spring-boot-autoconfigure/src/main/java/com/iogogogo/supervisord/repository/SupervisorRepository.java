//package com.iogogogo.supervisord.repository;
//
//import com.iogogogo.supervisord.base.BaseJpaRepository;
//import com.iogogogo.supervisord.domain.pojo.SupervisordExt;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.util.Collections;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by tao.zeng on 2021/7/1.
// */
//public class SupervisorRepository extends BaseJpaRepository<SupervisordExt, Long> {
//
//    public SupervisorRepository(EntityManager em) {
//        super(SupervisordExt.class, em);
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<SupervisordExt> findAll() {
//        Query query = em.createNativeQuery("select id, name, `group`, remark from supervisord", SupervisordExt.class);
//        List<SupervisordExt> list = query.getResultList();
//        return list != null && list.size() > 0 ? list : Collections.emptyList();
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public SupervisordExt save() {
//        String proc = "logstash_test-" + UUID.randomUUID();
//        return em.merge(new SupervisordExt(proc, proc, "logstash 测试"));
//    }
//}
