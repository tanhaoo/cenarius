package com.th.cenarius.web.config;

import com.th.cenarius.web.advice.base.customize.exceptionHandle.ExceptionHandleAspect;
import com.th.cenarius.web.advice.base.customize.exceptionHandle.ExceptionHandleHandler;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Aaron
 * @Date: 2022/11/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({ExceptionHandleHandler.class, ExceptionHandleAspect.class})
public @interface EnableExceptionHandleAspect {
}
