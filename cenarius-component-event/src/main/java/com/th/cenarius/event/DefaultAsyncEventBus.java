package com.th.cenarius.event;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
public class DefaultAsyncEventBus extends AbstractEventBus {
    /**
     * 单例
     */
    public final static DefaultAsyncEventBus INSTANCE = new DefaultAsyncEventBus();

    /**
     * 私有构造函数。
     * 调用方无法通过 new 操作创建该对象的实例，只能获取静态的单例。
     */
    private DefaultAsyncEventBus() {

        // 异步事件总线
        eventBus = new AsyncEventBus("domain-async-eventbus",
                new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                        60L, TimeUnit.SECONDS,
                        new SynchronousQueue<>(),
                        new EventBusThreadFactory()));
        // 初始化
        init();
    }

    static class EventBusThreadFactory implements ThreadFactory {

        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(0);
        private final String namePrefix;

        EventBusThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "eventbus-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
