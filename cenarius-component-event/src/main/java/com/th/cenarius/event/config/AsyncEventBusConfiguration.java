package com.th.cenarius.event.config;

import com.th.cenarius.event.EventBusCenter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
@Configuration
public class AsyncEventBusConfiguration {

    @Bean
    public EventBusCenter eventBusCenter() {
        return EventBusCenter.async();
    }
}
