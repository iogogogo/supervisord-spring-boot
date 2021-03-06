package com.iogogogo.supervisord4j.listener;

import com.iogogogo.supervisord.domain.SupervisordProcess;
import com.iogogogo.supervisord.event.SupervisordEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * // https://www.cnblogs.com/doudouxiaoye/p/5688629.html
 * <p>
 * <p>
 * Type superClz = source.getClass().getGenericSuperclass();
 * <p>
 * Type type = ((ParameterizedType) superClz).getActualTypeArguments()[0];
 *
 * <p>
 * <p>
 * Created by tao.zeng on 2021/6/24.
 */
@Slf4j
@Component
public class SupervisordEventListener implements ApplicationListener<SupervisordEvent> {

    @Override
    public void onApplicationEvent(SupervisordEvent supervisordEvent) {
        log.info("supervisordEvent: {}", supervisordEvent);
    }

    private void eventAllProcessInfo(Object source) {
        @SuppressWarnings("unchecked")
        List<SupervisordProcess> processList = (List<SupervisordProcess>) source;
        log.info("processList:{}", processList);
    }

    private void eventProcessInfo(Object source) {
        SupervisordProcess process = (SupervisordProcess) source;
        log.info("process:{}", process);
    }

    private void eventPid(Object source) {
        int pid = (int) source;
        log.info("pid:{}", pid);
    }
}
