package com.th.cenarius.lock.advice;

import com.th.cenarius.lock.annotation.RedisLock;
import com.th.cenarius.lock.utils.SpELUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class RedisLockAdvice {

    private final RedissonClient client;

    private final ApplicationContext context;

    public RedisLockAdvice(RedissonClient client, ApplicationContext context) {
        this.client = client;
        this.context = context;
    }

    @Pointcut("@annotation(com.th.cenarius.lock.annotation.RedisLock)")
    public void process() {

    }

    @Around("process()")
    public <T> Object aroundExec(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();
        RedisLock annotation = method.getAnnotation(RedisLock.class);
        String prefix = annotation.prefix();
        String value = annotation.value();
        String collection = annotation.collection();
        Class<T> expectType = annotation.expectType();

        String invoke = annotation.invoke();
        T o = (T) SpELUtils.parseExpression(invoke, context, point.getThis(), Object.class);
        System.err.println("o: " + o);

        Set<String> keys = new TreeSet<>();
        if (StringUtil.isNotEmpty(value)) {
            T t = SpELUtils.parseExpression(value, method, args, expectType);
            keys.add(prefix + t);
        } else if (StringUtil.isNotEmpty(collection)) {
            Collection lockCollection = SpELUtils.parseExpression(collection, method, args, Collection.class);
            lockCollection.stream().iterator().forEachRemaining(item -> keys.add(prefix + item));
        }
        Map<String, RLock> lock = this.lock(keys);
        try {
            return point.proceed();
        } finally {
            this.unlock(lock);
        }
    }

    private Map<String, RLock> lock(Set<String> keys) {
        Map<String, RLock> locks = new HashMap(6);

        for (String key : keys) {
            RLock lock = this.client.getLock(key);
            lock.lock();
            locks.put(key, lock);
            log.info("Succeed to create a distributed lock. key: {}", key);
        }
        return locks;
    }

    private void unlock(Map<String, RLock> locks) {

        for (String key : locks.keySet()) {
            locks.get(key).unlock();
            log.info("Succeed to unlock. key: {}", key);
        }

    }
}
