package com.th.cenarius.commons.pattern.pipeline.filter;

import com.th.cenarius.commons.model.BizContext;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
public abstract class AbstractBizFilter<T extends BizContext> implements BizFilter<T> {

    @Override
    public void doFilter(T context, BizFilterChain chain) {

        if (context.getFilterSelector().matchFilter(this.getClass().getSimpleName())) {
            this.handle(context);
        }

        if (context.continueChain()) {
            chain.fireNext(context);
        }

    }

    protected abstract void handle(T var1);
}
