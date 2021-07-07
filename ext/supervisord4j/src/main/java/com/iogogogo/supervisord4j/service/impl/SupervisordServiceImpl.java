package com.iogogogo.supervisord4j.service.impl;

import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.domain.SupervisordProcess;
import com.iogogogo.supervisord.exception.SupervisordException;
import com.iogogogo.supervisord.util.ListUtils;
import com.iogogogo.supervisord4j.pojo.entity.JobEntity;
import com.iogogogo.supervisord4j.repository.SupervisorRepository;
import com.iogogogo.supervisord4j.service.SupervisordService;
import org.apache.commons.lang3.StringUtils;
import org.jooq.lambda.Seq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tao.zeng on 2021/7/7.
 */
@Service
public class SupervisordServiceImpl implements SupervisordService {

    private final SupervisorRepository supervisorRepository;

    private final Supervisord supervisord;

    public SupervisordServiceImpl(SupervisorRepository supervisorRepository, Supervisord supervisord) {
        this.supervisorRepository = supervisorRepository;
        this.supervisord = supervisord;
    }

    @Override
    public ListUtils.PageWrapper<JobEntity> partition(int pageNo, int pageSize, String condition) throws SupervisordException, IOException {

        List<SupervisordProcess> processList = supervisord.getAllProcessInfo();

        if (StringUtils.isNotEmpty(condition)) {
            processList = processList.stream()
                    .filter(x -> StringUtils.contains(x.getName(), condition)).collect(Collectors.toList());
        }

        ListUtils.PageWrapper<SupervisordProcess> partition = ListUtils.partition(pageNo, pageSize, processList);

        List<SupervisordProcess> records = partition.getRecords();

        // EXT_LIST = query database job description ext
        Set<String> jobNameSet = records.stream().map(SupervisordProcess::getName).collect(Collectors.toSet());
        List<JobEntity> list = supervisorRepository.findByNameList(jobNameSet);

        Seq<JobEntity> confSeq = Seq.cast(list.stream(), JobEntity.class);

        Seq<SupervisordProcess> processSeq = Seq.cast(records.stream(), SupervisordProcess.class);
        List<JobEntity> collect = processSeq
                .leftOuterJoin(confSeq, (left, right) -> StringUtils.equals(left.getName(), right.getName()))
                .map(x -> {
                    SupervisordProcess process = x.v1();
                    JobEntity entity = new JobEntity();
                    BeanUtils.copyProperties(process, entity);
                    entity.setUptime(LocalTime.ofSecondOfDay(process.getNow() - process.getStart()));

                    JobEntity ext = x.v2();
                    if (Objects.nonNull(ext)) {
                        entity.setJobId(ext.getJobId());
                        entity.setServer(ext.getServer());
                        entity.setName(ext.getName());
                        entity.setRemark(ext.getRemark());
                        entity.setCreateTime(ext.getCreateTime());
                        entity.setUpdateTime(ext.getUpdateTime());
                    }
                    return entity;
                }).collect(Collectors.toList());

        return new ListUtils.PageWrapper<>(partition.getPageNo(),
                partition.getPageSize(),
                partition.getTotalSize(),
                collect);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JobEntity merge(JobEntity entity) {
        return supervisorRepository.save(entity);
    }
}
