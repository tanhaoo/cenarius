package com.th.cenarius.event;

import com.google.common.eventbus.EventBus;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
public class DefaultSyncEventBus extends AbstractEventBus {

    public static final DefaultSyncEventBus INSTANCE = new DefaultSyncEventBus();

    private DefaultSyncEventBus() {
        // 同步事件总线
        eventBus = new EventBus("domain-sync-eventbus");
        init();
    }
}
