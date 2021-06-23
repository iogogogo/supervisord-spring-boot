package com.iogogogo.supervisord.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * Created by tao.zeng on 2021/6/23.
 */
public class SupervisordEvent extends ApplicationEvent {

    public SupervisordEvent(Object source) {
        super(source);
    }

    public SupervisordEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
