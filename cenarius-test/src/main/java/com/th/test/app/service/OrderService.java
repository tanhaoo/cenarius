package com.th.test.app.service;

import com.th.cenarius.event.EventBusCenter;
import com.th.cenarius.lock.annotation.RedisLock;
import com.th.test.app.event.OrderSubmitEventById;
import com.th.test.app.event.OrderSubmitEventByOrder;
import com.th.test.app.event.OrderSubmitEventByUnknown;
import com.th.test.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@Service
public class OrderService {

    @Autowired
    EventBusCenter busCenter;

    /**
     * Submit
     * @param order {@link Order} instance
     */
    //    @RedisLock(value = "#order.id", prefix = "ORDER")
//    @RedisLock(collection = "#order.items", property = "#OrderItem.itemPrice.price", prefix = "Order:")
    @RedisLock(collection = "#order.codes", property = "#Integer", prefix = "Order:")
    public void submit(Order order) {

        busCenter.post(new OrderSubmitEventById(order.getId()));
        busCenter.post(new OrderSubmitEventByOrder(order));
        busCenter.post(new OrderSubmitEventByOrder(order));
        busCenter.post(new OrderSubmitEventByUnknown(order.toString()));
        System.out.println(order);
    }
}
