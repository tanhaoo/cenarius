package com.th.cenarius.commons.utils.filter.custom;

import com.th.cenarius.commons.utils.filter.DataFilter;
import com.th.cenarius.commons.utils.filter.NumberFilter;
import com.th.cenarius.commons.utils.filter.OrFilter;
import com.th.cenarius.commons.Order;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Author: Aaron
 * @Date: 2023/4/20
 */
public class DataFilterTest {

    List<Order> orders = new ArrayList<>();

    @BeforeEach
    public void initData() {
        for (int i = 0; i < 50; i++) {
            orders.add(new Order(String.valueOf(i), BigDecimal.valueOf(new Random().nextDouble() * 100), "order detail " + i));
        }
    }

    // 过滤出Order ID小于10或大于40的订单信息
    @Test
    public void testCase1() {

        DataFilter<Integer> less10 = new NumberFilter(10, NumberFilter.ComparisonOperator.LESS_THAN);
        DataFilter<Integer> greater40 = new NumberFilter(40, NumberFilter.ComparisonOperator.GREATER_THAN);
        // data < 10 || data > 40
        OrFilter<Integer> orFilter = new OrFilter<>(List.of(less10, greater40));

        List<Integer> origin = orders.stream()
                .map(Order::getOrderId)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        List<Integer> result = orders.stream()
                .map(Order::getOrderId)
                .map(Integer::valueOf)
                .filter(orFilter::filter)
                .collect(Collectors.toList());

        List<Integer> diff = origin
                .stream()
                .filter(a -> result.stream().noneMatch(a::equals))
                .collect(Collectors.toList());

        System.out.println(origin);
        System.out.println(result);
        System.out.println(diff);
    }

    // 过滤出Order ID小于10或大于40 且 订单金额大于50 的订单信息
    @Test
    public void testCase2() {
        DataFilter<Integer> less10 = new NumberFilter(10, NumberFilter.ComparisonOperator.LESS_THAN);
        DataFilter<Integer> greater40 = new NumberFilter(40, NumberFilter.ComparisonOperator.GREATER_THAN);
        OrFilter<Integer> orFilter = new OrFilter<>(List.of(less10, greater40));

        List<ImmutablePair<String, BigDecimal>> origin = orders.stream()
                .map(data -> ImmutablePair.of(data.getOrderId(), data.getAmount()))
                .collect(Collectors.toList());
        // data < 10 || data > 40
        List<Order> condition1 = orders.stream()
                .filter(data -> orFilter.filter(Integer.valueOf(data.getOrderId())))
                .collect(Collectors.toList());
        // amount > 50
        List<Order> condition2 = orders.stream()
                .filter(data -> data.getAmount().compareTo(new BigDecimal(50)) > 0)
                .collect(Collectors.toList());

        condition1.addAll(condition2);
        List<ImmutablePair<String, BigDecimal>> result = condition1
                .stream()
                .distinct()
                .map(data -> ImmutablePair.of(data.getOrderId(), data.getAmount()))
                .collect(Collectors.toList());

        System.out.println(origin);
        System.out.println(result);

    }


}
