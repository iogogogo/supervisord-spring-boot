package com.iogogogo.supervisord4j.util;


import java.util.UUID;

/**
 * Created by tao.zeng on 2019-07-24.
 */
public class IdHelper {


    private IdHelper() {
    }

    public static Long id() {
        return new Snowflake(2).next();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }
}
