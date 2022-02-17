package com.th.test;

import com.th.cenarius.lock.config.EnableRedisLockAdvice;
import com.th.cenarius.lock.utils.SpELUtils;
import com.th.test.app.OrderService;
import com.th.test.domain.Order;
import com.th.test.domain.OrderItem;
import com.th.test.domain.OrderPrice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@SpringBootTest
public class ApplicationTest {

    @Resource
    OrderService service;

    @BeforeEach
    public void initData() {

    }

    @Test
    public void test() {
        Order order = new Order();
        order.id = 100;
        List<OrderItem> items = new ArrayList<>();
        order.items = items;
        for (int i = 0; i < 10; i++) {
            items.add(new OrderItem(i, new OrderPrice(i + 100)));
        }

        service.submit(order);
    }

    @Test
    public void test1() throws NoSuchMethodException {
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] submits = discoverer.getParameterNames(OrderService.class.getMethod("submit", Order.class));
        System.out.println(submits);
    }

    @Test
    public void test2() {
        Order order = new Order();
        order.orderPrice = new OrderPrice(10);
        Field field = ReflectionUtils.findField(Order.class, "orderPrice");
        System.out.println(field);
    }

    @Test
    public void test3() {
        OrderItem orderItem = new OrderItem(20, new OrderPrice(100));
        Object o = SpELUtils.parseExpression("#item.itemPrice.price", orderItem);
        System.out.println(o);
    }
}
