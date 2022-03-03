package com.th.cenarius.event.utils;

import com.th.cenarius.event.EventBusCenter;
import com.th.cenarius.event.EventListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
@Component
@Slf4j
public class ApplicationContextUtils implements ApplicationContextAware {

    @Getter
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Bean
    public void registerListeners() {
        // log
        log.info("Start to register event listener...");
        final Map<String, EventListener> listenerMap = context.getBeansOfType(EventListener.class);

        listenerMap.values().forEach(item -> {
            // 注册事件处理器
            EventBusCenter.sync()
                    .register(item);
        });
        // log
        log.info("succeed to register event listeners, total size {}. type:{}",
                listenerMap.values().size(), listenerMap.keySet());
    }
}
