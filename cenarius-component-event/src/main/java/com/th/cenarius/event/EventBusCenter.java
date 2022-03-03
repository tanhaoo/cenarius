package com.th.cenarius.event;

import com.google.common.eventbus.EventBus;

/**
 * @Author: Aaron
 * @Date: 2022/3/2
 */
public interface EventBusCenter {
    /**
     * 注册事件处理器
     *
     * @param listener 处理器
     * @return {@link EventBus}
     */
    void register(EventListener listener);

    /**
     * 注销事件处理器
     *
     * @param listener 事件处理器
     * @return {@link EventBus}
     */
     void unregister(EventListener listener);

    /**
     * 注销所有事件处理器
     */
    void unregisterAll();

    /**
     * 发布事件
     *
     * @param event 事件
     */
    void post(Event event);

    /**
     * 获取同步事件总线
     *
     * @return 同步事件总线
     */
    static EventBusCenter sync() {
        return DefaultSyncEventBus.INSTANCE;
    }
//
//    /**
//     * 获取异步事件总线
//     *
//     * @return 异步事件总线
//     */
//    static EventBus async() {
//        return DefaultAsyncEventBus.INSTANCE;
//    }

}
