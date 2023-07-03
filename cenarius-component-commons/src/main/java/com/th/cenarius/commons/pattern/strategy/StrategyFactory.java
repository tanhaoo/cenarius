package com.th.cenarius.commons.pattern.strategy;

import com.google.common.collect.Maps;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
public class StrategyFactory<T, S extends Strategy<T>> implements InitializingBean, ApplicationContextAware {

    private Map<T, S> strategyFactory;

    private final Class<S> strategyType;

    private ApplicationContext context;

    /**
     * Get Strategy by id
     *
     * @param id identify of strategy
     * @return strategy
     */
    public S getStrategy(T id) {
        return strategyFactory.get(id);
    }

    /**
     * Strategy type
     *
     * @return type of strategy
     */
    protected Class<S> getStrategyType() {
        return this.strategyType;
    }

    public StrategyFactory(Class<S> strategyType) {
        this.strategyType = strategyType;
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
