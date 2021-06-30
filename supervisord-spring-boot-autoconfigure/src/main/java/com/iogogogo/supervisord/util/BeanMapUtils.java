package com.iogogogo.supervisord.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tao.zeng on 2021/6/22.
 */
@Slf4j
public class BeanMapUtils {

    /**
     * 将对象属性转化为map结合
     *
     * @param <T>  the type parameter
     * @param bean the bean
     * @return the map
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(String.valueOf(key), beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map集合中的数据转化为指定对象的同名属性中
     *
     * @param <T>   the type parameter
     * @param map   the map
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
            return bean;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("mapToBean exception ", e);
        }
        return null;
    }
}
