package com.th.test.app.event;

import com.th.cenarius.event.Event;
import com.th.test.domain.Order;

import lombok.AllArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
@AllArgsConstructor
public class OrderSubmitEventByOrder implements Event<Order> {

    private Order order;

    @Override
    public Order getTarget() {
        return order;
    }
}
