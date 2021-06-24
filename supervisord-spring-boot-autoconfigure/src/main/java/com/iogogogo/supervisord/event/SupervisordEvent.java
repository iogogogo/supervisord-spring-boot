package com.iogogogo.supervisord.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by tao.zeng on 2021/6/23.
 */
public class SupervisordEvent extends ApplicationEvent {

    private String method;

    public SupervisordEvent(Object source) {
        super(source);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
