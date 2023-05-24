package com.th.cenarius.commons.utils.filter.element;

import com.th.cenarius.commons.Order;

import net.bytebuddy.matcher.ElementMatcher.Junction.AbstractBase;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/5/17
 */
public class OrderPriceMatcher extends AbstractBase<Order> {
    @Override
    public boolean matches(Order target) {
        return target.getAmount().compareTo(new BigDecimal("50")) > 0;
    }
}
