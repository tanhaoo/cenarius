package com.th.cenarius.commons.pattern.pipeline.filter;

import com.th.cenarius.commons.pattern.pipeline.model.BizContext;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
@AllArgsConstructor
public class DefaultFilterChain<T extends BizContext> implements BizFilterChain<T> {

    private BizFilter<T> filter;

    @Setter
    private BizFilterChain<T> next;

    public DefaultFilterChain(BizFilter<T> filter) {
        this.filter = filter;
    }

    @Override
    public void handle(T context) {
        filter.doFilter(context, this);
    }

    @Override
    public void fireNext(T context) {
        BizFilterChain<T> nextChain = this.next;
        if (Objects.nonNull(nextChain)) {
            nextChain.handle(context);
        }
    }
}
