package com.th.cenarius.web.advice.base;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: Aaron
 * @Date: 2022/11/28
 */
public abstract class BaseMethodAspect implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * 切点，通过 @Pointcut 指定相关的注解
     */
    protected abstract void pointcut();

    /**
     * 对目标方法进行环绕增强处理，子类需通过 pointcut() 方法指定切点
     *
     * @param point 连接点
     * @return 方法执行返回值
     */
    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint point) {
        Class<? extends MethodAdviceHandler<?>> handlerType = getAdviceHandlerType();
        MethodAdviceHandler<?> adviceHandler = context.getBean(handlerType);
        return advice(point, adviceHandler);
    }


    /**
     * 获得切面绑定的方法增强处理器的类型
     *
     * @return 增强处理器
     */
    protected abstract Class<? extends MethodAdviceHandler<?>> getAdviceHandlerType();

    private Object advice(ProceedingJoinPoint point, MethodAdviceHandler<?> handler) {
        // 执行之前，返回是否被允许执行
        boolean permitted = handler.onBefore(point);

        // 方法返回值
        Object result;
        boolean thrown = false;
        // 开始执行的时间
        long startTime = System.currentTimeMillis();

        if (permitted) {
            try {
                result = point.proceed();
            } catch (Throwable e) {
                // 抛出异常
                thrown = true;
                // 处理异常
                handler.onThrow(point, e);
                // 抛出异常时的返回值
                result = handler.getOnThrow(point, e);
            }
        } else {
            result = handler.getOnForbid(point);
        }

        // 结束
        handler.onComplete(point, startTime, permitted, thrown, result);
        return result;
    }

}
