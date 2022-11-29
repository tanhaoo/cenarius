package com.th.cenarius.web.config;

import com.th.cenarius.web.advice.base.customize.invokeRecord.InvokeRecordAspect;
import com.th.cenarius.web.advice.base.customize.invokeRecord.InvokeRecordHandler;

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
@Import({InvokeRecordAspect.class, InvokeRecordHandler.class})
public @interface EnableInvokeRecordAspect {
}
