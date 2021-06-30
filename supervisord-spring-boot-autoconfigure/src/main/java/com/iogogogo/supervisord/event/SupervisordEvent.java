package com.iogogogo.supervisord.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by tao.zeng on 2021/6/23.
 */
public class SupervisordEvent extends ApplicationEvent {

    private String method;

    /**
     * Instantiates a new Supervisord event.
     *
     * @param source the source
     */
    public SupervisordEvent(Object source) {
        super(source);
    }

    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets method.
     *
     * @param method the method
     */
    public void setMethod(String method) {
        this.method = method;
    }
}
