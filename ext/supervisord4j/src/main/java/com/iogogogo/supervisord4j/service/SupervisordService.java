package com.iogogogo.supervisord4j.service;

import com.iogogogo.supervisord.exception.SupervisordException;
import com.iogogogo.supervisord.util.ListUtils;
import com.iogogogo.supervisord4j.pojo.entity.JobEntity;

import java.io.IOException;

/**
 * Created by tao.zeng on 2021/7/7.
 */
public interface SupervisordService {

    ListUtils.PageWrapper<JobEntity> partition(int pageNo, int pageSize, String condition) throws SupervisordException, IOException;

    JobEntity merge(Long jobId, JobEntity entity);
}
