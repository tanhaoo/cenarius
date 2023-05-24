package com.th.cenarius.commons.utils.filter.element;

import com.th.cenarius.commons.Order;

import net.bytebuddy.matcher.ElementMatcher.Junction.AbstractBase;

/**
 * @Author: Aaron
 * @Date: 2023/5/17
 */
public class OrderNumberMatcher extends AbstractBase<Order> {
    @Override
    public boolean matches(Order order) {
        return Integer.parseInt(order.getOrderId()) > 30;
    }
}
