package com.example.pojo.entity;

import com.iogogogo.supervisord.domain.SupervisordProcess;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by tao.zeng on 2021/7/7.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JobEntity extends SupervisordProcess {

    private String descriptionExt;
}
