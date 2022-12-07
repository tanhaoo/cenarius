package com.th.cenarius.web.common.strategy;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
public interface Strategy<T> {

    /**
     * Get identify
     *
     * @return ID
     */
    T getId();
}
