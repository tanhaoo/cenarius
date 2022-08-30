package com.th.cenarius.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/3/2
 */
@Slf4j
public class AbstractEventBus implements EventBusCenter {

    /**
     * 事件列表
     */
    private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

    /**
     * 异步事件总线
     */
    protected EventBus eventBus;

    protected AbstractEventBus() {

    }

    protected void init() {
        if (eventBus != null) {
            eventBus.register(
                    new Object() {
                        // It is called when method post() is executed
                        @Subscribe
                        public void onEvent(Event event) {
                            listeners.stream()
                                    .filter(listener -> listener.support(event))
                                    .forEach(listener -> {
                                        listener.onEvent(event);
                                        log.info("succeed to process event. type:{}", event.getClass().getSimpleName());
                                    });
                        }
                    });
        }
    }


    @Override
    public void register(EventListener listener) {
        if (!listeners.contains(listener)) {
            // 同一个实例不能重复注册，因为没有重写equals，再加上在getBeansOfType时已经去重了，所以这边contain无意义
            listeners.add(listener);
        }
    }

    @Override
    public void unregister(EventListener listener) {
        listeners.remove(listener);
        // log
        log.info("succeed to unregister a event listener. type:{}", listener.getClass().getSimpleName());
    }

    @Override
    public void unregisterAll() {
        listeners.clear();
    }

    @Override
    public void post(Event event) {
        eventBus.post(event);
    }
}
