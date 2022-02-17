package com.th.test.app;

import com.th.cenarius.lock.annotation.RedisLock;
import com.th.test.domain.Order;

import org.springframework.stereotype.Service;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@Service
public class OrderService {

    //    @RedisLock(value = "#order.id", prefix = "ORDER")
    @RedisLock(collection = "#order.items", property = "#OrderItem.itemPrice.price", prefix = "Order:")
    public void submit(Order order) {
        System.out.println(order);
    }
}
