package com.th.test;

import com.th.cenarius.event.config.EnableAsyncEventBus;
import com.th.cenarius.web.advice.DefaultResponseEntityExceptionHandler;
import com.th.cenarius.web.advice.ResponseBodyHandlerAdvice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableRedisLockAdvice
@EnableAsyncEventBus
//@EnableSyncEventBus
@Import({ResponseBodyHandlerAdvice.class,
        DefaultResponseEntityExceptionHandler.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
