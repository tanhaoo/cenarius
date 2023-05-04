package com.th.cenarius.commons.utils.filter;

import lombok.AllArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/4/20
 */
@AllArgsConstructor
public class NotFilter<T> implements DataFilter<T> {

    private DataFilter<T> dataFilter;

    @Override
    public boolean filter(T data) {
        return !dataFilter.filter(data);
    }

}
