package com.iogogogo.supervisord.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by tao.zeng on 2021/06/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SupervisordException extends Exception {

    private int code;

    private String message;

    public SupervisordException(String message) {
        super(message);
    }
}