package com.th.cenarius.event;

/**
 * @Author: Aaron
 * @Date: 2022/3/2
 */
public interface Event<E> {
    E getTarget();
}
