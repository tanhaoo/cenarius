package com.th.cenarius.web.advice.base.customize.invokeRecord;

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
@Order(1)
public class InvokeRecordAspect extends BaseMethodAspect {

    /**
     * 指定切点（处理打上 InvokeRecordAnno 的方法）
     */
    @Pointcut("@annotation(com.th.cenarius.web.annotation.InvokeRecordAnno)")
    @Override
    protected void pointcut() {
    }

    /**
     * 指定该切面绑定的方法切面处理器为 InvokeRecordHandler
     */
    @Override
    protected Class<? extends MethodAdviceHandler<?>> getAdviceHandlerType() {
        return InvokeRecordHandler.class;
    }
}
