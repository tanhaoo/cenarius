package com.th.test;

import com.th.cenarius.event.EventListener;
import com.th.cenarius.lock.utils.SpELUtils;
import com.th.test.app.event.OrderSubmitEventById;
import com.th.test.app.event.OrderSubmitEventByOrder;
import com.th.test.app.event.listener.OrderSubmitEventListenerByOrder;
import com.th.test.app.service.OrderService;
import com.th.test.domain.Order;
import com.th.test.domain.OrderItem;
import com.th.test.domain.OrderPrice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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

    Order order;
    List<OrderItem> items = new ArrayList<>();
    List<Integer> codes = new ArrayList<>();

    @BeforeEach
    public void initData() {
        order = new Order();
        order.id = 100;
        order.items = items;
        order.codes = codes;
        for (int i = 0; i < 10; i++) {
            items.add(new OrderItem(i, new OrderPrice(i + 100)));
            order.getCodes().add(i);
        }
    }

    @Test
    public void test() {
        service.submit(order);
        while (true) ;
    }


    @Test
    public void test1() throws NoSuchMethodException {
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] submits = discoverer.getParameterNames(OrderService.class.getMethod("submit", Order.class));
        System.out.println(Arrays.toString(submits));
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
        Object o = SpELUtils.parseExpression("#OrderItem.itemPrice.price", orderItem);
        System.out.println(o);
    }

    @Test
    public void testEvent() {
        System.out.println(order);
        OrderSubmitEventByOrder eventByOrder = new OrderSubmitEventByOrder(order);
        OrderSubmitEventById eventById = new OrderSubmitEventById(order.getId());
        OrderSubmitEventListenerByOrder eventListenerByOrder = new OrderSubmitEventListenerByOrder();
        System.out.println(eventListenerByOrder.support(eventById));

//        Type[] genericInterfaces = eventListenerByOrder.getClass().getGenericInterfaces();
//        Type genericSuperclass = eventListenerByOrder.getClass().getGenericSuperclass();
        System.out.println(1);
    }

    @Test
    public void testRepeatedListener() {
        List<EventListener> list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            OrderSubmitEventListenerByOrder eventById = new OrderSubmitEventListenerByOrder();
            if (!list.contains(eventById))
                list.add(eventById);
        }
        System.out.println(list);
    }
}
