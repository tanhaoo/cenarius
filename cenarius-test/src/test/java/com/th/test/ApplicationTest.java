package com.th.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.th.cenarius.event.EventListener;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;
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
@ActiveProfiles("test")
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
    public void testSubmitOrder() {
        service.submit(order);
    }


    @Test
    public void testGetMethodParameter() throws NoSuchMethodException {
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] submits = discoverer.getParameterNames(OrderService.class.getMethod("submit", Order.class));
        System.out.println(Arrays.toString(submits));
    }

    @Test
    public void testGetField() {
        Order order = new Order();
        order.orderPrice = new OrderPrice(10);
        Field field = ReflectionUtils.findField(Order.class, "orderPrice");
        assert field != null;
        ReflectionUtils.setField(field, order, new OrderPrice(20));
//        field.setAccessible(true);
//        field.set(order, new OrderPrice(20));
        System.out.println(order.orderPrice);
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

    @Test
    public void testSerialize() throws JsonProcessingException {
        String json = JSON.toJSONString(order.getItems());
        ObjectMapper mapper = new ObjectMapper();

        List<OrderItem> copyOrderItems = mapper.readValue(json, new TypeReference<>() {
        });
        System.out.println(copyOrderItems);

    }

}
