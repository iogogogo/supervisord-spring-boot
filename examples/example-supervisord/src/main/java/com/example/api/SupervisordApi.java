package com.example.api;

import com.example.pojo.entity.JobEntity;
import com.google.common.collect.Lists;
import com.iogogogo.supervisord.base.BaseResult;
import com.iogogogo.supervisord.base.ResponseWrapper;
import com.iogogogo.supervisord.core.Supervisord;
import com.iogogogo.supervisord.domain.SupervisordProcess;
import com.iogogogo.supervisord.exception.SupervisordException;
import com.iogogogo.supervisord.util.ListUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jooq.lambda.Seq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tao.zeng on 2021/7/7.
 */
@RestController
@RequestMapping("/api/supervisord")
public class SupervisordApi implements BaseResult {

    private final static List<DescriptionExt> EXT_LIST = Lists.newArrayList();

    static {
        EXT_LIST.add(new DescriptionExt("logstash_test", "这是Logstash测试"));
        EXT_LIST.add(new DescriptionExt("xxx", "哈哈哈哈哈"));
    }

    @Autowired
    private Supervisord supervisord;

    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseWrapper<?> list(@PathVariable("pageNo") long pageNo, @PathVariable("pageSize") long pageSize) throws SupervisordException, IOException {
        List<SupervisordProcess> processList = supervisord.getAllProcessInfo();

        ListUtils.PageWrapper<SupervisordProcess> partition = ListUtils.partition(pageNo, pageSize, processList);

        List<SupervisordProcess> records = partition.getRecords();

        Set<String> jobNameSet = records.stream().map(SupervisordProcess::getName).collect(Collectors.toSet());

        // EXT_LIST = query database job description ext
        Seq<DescriptionExt> confSeq = Seq.cast(EXT_LIST.stream(), DescriptionExt.class);

        Seq<SupervisordProcess> processSeq = Seq.cast(records.stream(), SupervisordProcess.class);
        List<JobEntity> collect = processSeq
                .leftOuterJoin(confSeq, (left, right) -> StringUtils.equals(left.getName(), right.getName()))
                .map(x -> {
                    JobEntity entity = new JobEntity();
                    BeanUtils.copyProperties(x.v1(), entity);
                    DescriptionExt ext = x.v2();
                    if (Objects.nonNull(ext))
                        entity.setDescriptionExt(ext.getDescriptionExt());
                    return entity;
                }).collect(Collectors.toList());

        ListUtils.PageWrapper<JobEntity> pageWrapper = new ListUtils.PageWrapper<>();
        BeanUtils.copyProperties(partition, pageWrapper);
        pageWrapper.setRecords(collect);

        return ok(pageWrapper);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class DescriptionExt implements Serializable {

        private String name;

        private String descriptionExt;
    }
}
