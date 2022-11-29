package com.th.cenarius.web.advice.base.customize.exceptionHandle;

import com.th.cenarius.web.advice.base.BaseMethodAdviceHandler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/11/28
 */
@Component
@Slf4j
public class ExceptionHandleHandler extends BaseMethodAdviceHandler<Object> {
    @Override
    public void onThrow(ProceedingJoinPoint point, Throwable e) {
        // 可以做发邮件通知等业务
        log.info("Send email to notify the error, method name:{} ", getMethodDesc(point));
    }
}
