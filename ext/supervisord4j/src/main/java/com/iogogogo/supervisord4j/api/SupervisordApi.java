package com.iogogogo.supervisord4j.api;

import com.iogogogo.supervisord.base.BaseResult;
import com.iogogogo.supervisord.base.ResponseWrapper;
import com.iogogogo.supervisord.exception.SupervisordException;
import com.iogogogo.supervisord4j.pojo.entity.JobEntity;
import com.iogogogo.supervisord4j.service.SupervisordService;
import com.iogogogo.supervisord4j.util.IdHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by tao.zeng on 2021/7/7.
 */
@Api(value = "supervisord4j", tags = "Supervisord api")
@RestController
@RequestMapping("/api/supervisord")
public class SupervisordApi implements BaseResult {

    private final SupervisordService supervisordService;

    public SupervisordApi(SupervisordService supervisordService) {
        this.supervisordService = supervisordService;
    }

    @ApiOperation(value = "获取supervisord所有运行进程")
    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseWrapper<?> list(@PathVariable("pageNo") int pageNo,
                                   @PathVariable("pageSize") int pageSize,
                                   @RequestParam(required = false, value = "condition") String condition) throws SupervisordException, IOException {

        return ok(supervisordService.partition(pageNo, pageSize, condition));
    }

    @ApiOperation(value = "保存job扩展信息")
    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public ResponseWrapper<?> save(@RequestBody JobEntity entity) {
        entity.setJobId(IdHelper.id());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        return ok(supervisordService.merge(entity));
    }

    @ApiOperation(value = "修改job扩展信息")
    @RequestMapping(value = "/{jobId}", method = {RequestMethod.PUT})
    public ResponseWrapper<?> update(@PathVariable("jobId") Long jobId, @RequestBody JobEntity entity) {
        entity.setJobId(jobId);
        entity.setUpdateTime(LocalDateTime.now());
        return ok(supervisordService.merge(entity));
    }
}
