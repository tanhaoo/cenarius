package com.th.cenarius.event.config;

import com.th.cenarius.event.DefaultSyncEventBus;
import com.th.cenarius.event.EventBusCenter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
@Configuration
public class SyncEventBusConfiguration {

    @Bean
    public EventBusCenter eventBusCenter() {
        return EventBusCenter.sync();
    }
}
