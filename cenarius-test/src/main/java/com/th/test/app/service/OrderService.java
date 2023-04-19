package com.th.test.app.service;

import com.th.cenarius.event.EventBusCenter;
import com.th.cenarius.lock.annotation.RedisLock;
import com.th.test.app.event.OrderSubmitEventById;
import com.th.test.app.event.OrderSubmitEventByOrder;
import com.th.test.app.event.OrderSubmitEventByUnknown;
import com.th.test.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@Service
public class OrderService {

    @Autowired
    EventBusCenter busCenter;

    public Order simpleOrder = Order.builder().id(200).build();

    /**
     * Submit
     *
     * @param order {@link Order} instance
     */
    //    @RedisLock(value = "#order.id", prefix = "ORDER")
    @RedisLock(value = "#order.codes[6]", expectType = String.class, prefix = "Order:", invoke = "@orderService.printOrder(@orderService.getOrder())")
//    @RedisLock(collection = "#order.codes", prefix = "Order:")
    public void submit(@Valid Order order) {

        busCenter.post(new OrderSubmitEventById(order.getId()));
        busCenter.post(new OrderSubmitEventByOrder(order));
        busCenter.post(new OrderSubmitEventByOrder(order));
        busCenter.post(new OrderSubmitEventByUnknown(order.toString()));
        System.out.println(order);
    }

    public Order getOrder() {
        return Order.builder().id(199).build();
    }

    public Order printOrder(Order order) {
        System.out.println(order);
        return order;
    }
}
