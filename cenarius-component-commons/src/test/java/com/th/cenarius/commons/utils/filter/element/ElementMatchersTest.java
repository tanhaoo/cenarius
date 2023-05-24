package com.th.cenarius.commons.utils.filter.element;

import com.th.cenarius.commons.Order;

import net.bytebuddy.matcher.ElementMatchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: Aaron
 * @Date: 2023/5/17
 */
public class ElementMatchersTest {

    List<Order> orders = new ArrayList<>();

    @BeforeEach
    public void initData() {
        for (int i = 0; i < 50; i++) {
            orders.add(new Order(String.valueOf(i), BigDecimal.valueOf(new Random().nextDouble() * 100), "order detail " + i));
        }
    }

    @Test
    public void testAnd() {
        orders.stream()
                .filter(item ->
                        ElementMatchers.any()
                                .and(new OrderNumberMatcher())
                                .and(new OrderPriceMatcher())
                                .matches(item))
                .forEach(System.out::println);

    }
}
