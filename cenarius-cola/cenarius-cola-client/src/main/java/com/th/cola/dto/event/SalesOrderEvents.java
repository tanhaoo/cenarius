package com.th.cola.dto.event;

import com.th.cola.dto.SalesOrderAddCmd;

import org.springframework.context.ApplicationEvent;

import lombok.EqualsAndHashCode;
import lombok.Value;

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
