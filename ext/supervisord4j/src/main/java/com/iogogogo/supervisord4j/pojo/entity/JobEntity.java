package com.iogogogo.supervisord4j.pojo.entity;

import com.iogogogo.supervisord.domain.SupervisordProcess;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by tao.zeng on 2021/7/7.
 */
@Data
@Entity
@Table(name = "supervisord_job")
@EqualsAndHashCode(callSuper = true)
public class JobEntity extends SupervisordProcess {

    @Id
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "server")
    private String server;

    @Column(name = "name")
    private String name;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public JobEntity() {
    }
}
