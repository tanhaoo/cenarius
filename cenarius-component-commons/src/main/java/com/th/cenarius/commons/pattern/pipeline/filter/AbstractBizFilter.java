package com.th.cenarius.commons.pattern.pipeline.filter;

import com.th.cenarius.commons.pattern.pipeline.model.BizContext;

import java.lang.reflect.ParameterizedType;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
@Slf4j
public abstract class AbstractBizFilter<T extends BizContext> implements BizFilter<T> {

    @Override
    public void doFilter(T context, BizFilterChain chain) {

        if (context.getFilterSelector().matchFilter(this.getClass().getSimpleName())) {
            log.info("Pipeline start, context: {}, filter: {}",
                    ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName(),
                    this.getClass().getSimpleName());

            this.handle(context);
        }

        // 继续执行或直接停止
        if (context.continueChain()) {
            chain.fireNext(context);
        }

    }

    protected abstract void handle(T var1);
}
