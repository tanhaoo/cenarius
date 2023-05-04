package com.th.cenarius.commons.utils.filter;

import java.util.List;

import lombok.AllArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/4/20
 */
@AllArgsConstructor
public class AndFilter<T> implements DataFilter<T> {

    private List<DataFilter<T>> filters;

    @Override
    public boolean filter(T data) {
        for (DataFilter<T> dataFilter : filters) {
            if (!dataFilter.filter(data)) {
                return false;
            }
        }
        return true;
    }
}
