package com.th.cenarius.event.config;

import com.th.cenarius.event.utils.ApplicationContextUtils;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({SyncEventBusConfiguration.class, ApplicationContextUtils.class})
public @interface EnableSyncEventBus {
}
