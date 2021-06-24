package com.iogogogo.supervisord.annotation;

import com.iogogogo.supervisord.SupervisordAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by tao.zeng on 2021/6/21.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {SupervisordAutoConfiguration.class})
public @interface EnableSupervisord {
}
