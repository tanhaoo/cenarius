package com.th.cenarius.web.common;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
public interface Strategy<T> {

    /**
     * Get ID
     *
     * @return ID
     */
    T getId();
}
