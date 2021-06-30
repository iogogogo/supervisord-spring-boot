package com.iogogogo.supervisord.base;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by tao.zeng on 2021/6/25.
 *
 * @param <T> the type parameter
 */
@Data
@Builder
@ToString
public class ResponseWrapper<T> implements Serializable {

    private int code;

    private String message;

    private T data;
}
