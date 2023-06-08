package com.th.cenarius.commons.model;

import com.th.cenarius.commons.pattern.pipeline.filter.selector.FilterSelector;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
public abstract class AbstractBizContext implements BizContext {

    private final IBizEnum bizEnum;

    private final FilterSelector selector;

    protected AbstractBizContext(IBizEnum bizEnum, FilterSelector selector) {
        this.bizEnum = bizEnum;
        this.selector = selector;
    }

    public IBizEnum getBizCode() {
        return bizEnum;
    }

    public FilterSelector getFilterSelector() {
        return selector;
    }
}
