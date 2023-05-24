package com.th.cenarius.commons.model;

import com.th.cenarius.commons.pattern.pipeline.filter.selector.FilterSelector;

import lombok.AllArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
@AllArgsConstructor
public abstract class AbstractBizContext implements BizContext {

    private final BizEnum bizEnum;

    private final FilterSelector selector;

    public BizEnum getBizCode() {
        return bizEnum;
    }

    public FilterSelector getFilterSelector() {
        return selector;
    }
}
