package com.th.cenarius.lock.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RedisLock {

    String prefix() default "";

    @AliasFor("key")
    String value() default "";

    @AliasFor("value")
    String key() default "";

    String collection() default "";

    String property() default "";

}
