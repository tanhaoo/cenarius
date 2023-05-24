package com.th.cenarius.commons.pattern.pipeline.filter;

import com.th.cenarius.commons.model.BizContext;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
public interface BizFilter<T extends BizContext> {

    void doFilter(T context, BizFilterChain chain);
}
