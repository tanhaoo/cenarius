package com.th.cenarius.commons.utils.filter;

/**
 * @Author: Aaron
 * @Date: 2023/4/20
 */
public interface DataFilter<DATA> {

    /**
     * Filter business data
     *
     * @param data business data
     * @return result
     */
    boolean filter(DATA data);
}
