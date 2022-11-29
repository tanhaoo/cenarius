package com.th.cenarius.web.advice.base.customize.exceptionHandle;

import com.th.cenarius.web.advice.base.BaseMethodAspect;
import com.th.cenarius.web.advice.base.MethodAdviceHandler;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Aaron
 * @Date: 2022/11/28
 */
@Aspect
@Component
@Order(10)
public class ExceptionHandleAspect extends BaseMethodAspect {


    @Pointcut("@annotation(com.th.cenarius.web.annotation.ExceptionHandleAnno)")
    @Override
    protected void pointcut() {

    }

    @Override
    protected Class<? extends MethodAdviceHandler<?>> getAdviceHandlerType() {
        return ExceptionHandleHandler.class;
    }
}
