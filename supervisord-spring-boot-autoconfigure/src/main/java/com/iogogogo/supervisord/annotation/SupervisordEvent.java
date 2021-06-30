package com.iogogogo.supervisord.annotation;

import java.lang.annotation.*;

/**
 * Created by tao.zeng on 2021/6/25.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupervisordEvent {
}
