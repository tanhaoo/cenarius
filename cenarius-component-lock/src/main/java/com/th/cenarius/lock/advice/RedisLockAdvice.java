package com.th.cenarius.lock.advice;

import com.sun.source.util.Trees;
import com.th.cenarius.lock.annotation.RedisLock;
import com.th.cenarius.lock.utils.SpELUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

    public RedisLockAdvice(RedissonClient client) {
        this.client = client;
    }

    @Pointcut("@annotation(com.th.cenarius.lock.annotation.RedisLock)")
    public void process() {

    }

    @Around("process()")
    public Object aroundExec(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();
        RedisLock annotation = method.getAnnotation(RedisLock.class);
        String prefix = annotation.prefix();
        String value = annotation.value();
        String collection = annotation.collection();
        String property = annotation.property();

        Set<String> keys = new TreeSet<>();
        if (StringUtil.isNotEmpty(value))
            keys.add(prefix + SpELUtils.parseExpression(value, method, args, String.class));
        else if (StringUtil.isNotEmpty(collection) && StringUtil.isNotEmpty(property)) {
            Collection valOfCollection = SpELUtils.parseExpression(collection, method, args, Collection.class);
            valOfCollection.stream().iterator().forEachRemaining(item -> keys.add(prefix + SpELUtils.parseExpression(property, item)));
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
        Iterator iterator = keys.iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            RLock lock = this.client.getLock(key);
            lock.lock();
            locks.put(key, lock);
            log.info("Succeed to create a distributed lock. key: {}", key);
        }
        return locks;
    }

    private void unlock(Map<String, RLock> locks) {
        Iterator var2 = locks.keySet().iterator();

        while (var2.hasNext()) {
            String key = (String) var2.next();
            locks.get(key).unlock();
            log.info("Succeed to unlock. key: {}", key);
        }

    }
}
