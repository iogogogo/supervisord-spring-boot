package com.iogogogo.supervisord4j.repository;

import com.google.common.collect.Lists;
import com.iogogogo.supervisord4j.base.BaseJpaRepository;
import com.iogogogo.supervisord4j.pojo.entity.JobEntity;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

/**
 * Created by tao.zeng on 2021/7/1.
 * <p>
 * jps in https://stackoverflow.com/questions/9321916/jpa-criteriabuilder-how-to-use-in-comparison-operator
 */
@Repository
public class SupervisorRepository extends BaseJpaRepository<JobEntity, Long> {

    private final Environment environment;

    public SupervisorRepository(EntityManager em, Environment environment) {
        super(JobEntity.class, em);
        this.environment = environment;
    }


    public List<JobEntity> findByNameList(Collection<String> nameList) {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        String server = environment.getProperty("supervisord.url");

        CriteriaQuery<JobEntity> criteria = builder.createQuery(JobEntity.class);

        Root<JobEntity> root = criteria.from(JobEntity.class);

        List<Predicate> predicates = Lists.newArrayList();

        // in query
        CriteriaBuilder.In<String> in = builder.in(root.get("name"));
        nameList.forEach(x -> predicates.add(in.value(x)));

        // eq query
        predicates.add(builder.equal(root.get("server"), server));

        // execute query
        CriteriaQuery<JobEntity> query = criteria.select(root).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(query).getResultList();
    }
}
