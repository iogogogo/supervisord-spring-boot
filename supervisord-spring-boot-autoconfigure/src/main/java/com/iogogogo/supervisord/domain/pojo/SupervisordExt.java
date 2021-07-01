package com.iogogogo.supervisord.domain.pojo;

import com.iogogogo.supervisord.domain.SupervisordProcess;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by tao.zeng on 2021/7/1.
 */
@Data
public class SupervisordExt implements Serializable {

    private Long id;

    private String name;

    private String remark;

    private SupervisordProcess supervisordProcess;
}
