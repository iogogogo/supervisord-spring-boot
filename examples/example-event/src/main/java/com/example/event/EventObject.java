package com.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

import java.util.StringJoiner;

/**
 * Created by tao.zeng on 2021/6/23.
 */
@Slf4j
public class EventObject extends ApplicationEvent {

    private String method;

    private String name;

    private int state;

    public EventObject(Object source) {
        super(source);
    }

    public void doEvent(Object event) {
        log.info("doEvent:{}", event);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EventObject.class.getSimpleName() + "[", "]")
                .add("method='" + method + "'")
                .add("name='" + name + "'")
                .add("state=" + state)
                .toString();
    }
}
