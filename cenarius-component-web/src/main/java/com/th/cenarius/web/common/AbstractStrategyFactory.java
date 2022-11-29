package com.th.cenarius.web.common;

import com.google.common.collect.Maps;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
public abstract class AbstractStrategyFactory<T, S extends Strategy<T>> implements InitializingBean, ApplicationContextAware {

    private Map<T, S> strategyFactory;

    private ApplicationContext context;

    /**
     * Get Strategy by id
     *
     * @param id ID of strategy
     * @return strategy
     */
    public S getStrategy(T id) {
        return strategyFactory.get(id);
    }

    /**
     * 通过反射获取策略的类型
     *
     * @return 策略的类型
     */
    protected Class<S> getStrategyType() {
        // getClass 获取当前运行时实例的类，getGenericSuperclass 获得泛型父类
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) superclass;
        Type[] actualTypeArguments = pt.getActualTypeArguments();
        // 获得索引为 1 的实际参数类型，即第二个实际参数的类型
        Type actualTypeArgument = actualTypeArguments[1];
        @SuppressWarnings("unchecked")
        Class<S> result = (Class<S>) actualTypeArgument;

        return result;
    }

    @Override
    public void afterPropertiesSet() {
        Collection<S> values = context.getBeansOfType(getStrategyType()).values();
        strategyFactory = Maps.newHashMapWithExpectedSize(values.size());
        values.forEach(item -> strategyFactory.put(item.getId(), item));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
