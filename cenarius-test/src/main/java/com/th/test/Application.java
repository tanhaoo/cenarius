package com.th.test;

import com.th.cenarius.event.config.EnableAsyncEventBus;
import com.th.cenarius.event.config.EnableSyncEventBus;
import com.th.cenarius.lock.config.EnableRedisLockAdvice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@SpringBootApplication
@EnableRedisLockAdvice
@EnableAsyncEventBus
//@EnableSyncEventBus
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
