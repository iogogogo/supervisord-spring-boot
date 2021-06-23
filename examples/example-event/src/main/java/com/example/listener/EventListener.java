package com.example.listener;

import com.example.event.EventObject;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by tao.zeng on 2021/6/23.
 */
@Component
public class EventListener implements ApplicationListener<EventObject> {

    @Override
    public void onApplicationEvent(EventObject eventObject) {
        eventObject.doEvent(eventObject);
    }
}
