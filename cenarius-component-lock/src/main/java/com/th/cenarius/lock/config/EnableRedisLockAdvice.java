package com.th.cenarius.lock.config;

import com.th.cenarius.lock.advice.RedisLockAdvice;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(RedisLockAdvice.class)
public @interface EnableRedisLockAdvice {
}
