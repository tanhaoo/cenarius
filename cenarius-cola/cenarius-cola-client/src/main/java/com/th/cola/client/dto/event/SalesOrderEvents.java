package com.th.cola.client.dto.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
public interface SalesOrderEvents {

    class SalesOrderCreateEvent extends ApplicationEvent {
        public SalesOrderCreateEvent(Object source) {
            super(source);
        }
    }


}
