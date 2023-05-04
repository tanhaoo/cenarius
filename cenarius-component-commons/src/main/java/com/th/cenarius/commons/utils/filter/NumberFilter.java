package com.th.cenarius.commons.utils.filter;

import lombok.AllArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/4/20
 */
@AllArgsConstructor
public class NumberFilter implements DataFilter<Integer> {

    private Integer target;

    private ComparisonOperator operator;

    @Override
    public boolean filter(Integer value) {
        switch (operator) {
            case EQUALS:
                return value.equals(target);
            case GREATER_THAN:
                return value > target;
            case LESS_THAN:
                return value < target;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public enum ComparisonOperator {
        /**
         * Operator
         */
        EQUALS,
        GREATER_THAN,
        LESS_THAN
    }
}
