package com.th.cenarius.commons.model;

import com.th.cenarius.commons.pattern.pipeline.filter.selector.FilterSelector;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
public interface BizContext {

    /**
     * Get business code
     *
     * @return {@link BizEnum} enum
     */
    IBizEnum getBizCode();

    /**
     * Get filter selector
     *
     * @return {@link FilterSelector} selector
     */
    FilterSelector getFilterSelector();

    /**
     * Get if want to continue
     *
     * @return flag of continue
     */
    boolean continueChain();

}
