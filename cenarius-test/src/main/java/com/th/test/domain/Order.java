package com.th.test.domain;

import java.util.List;

import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@Data
public class Order {

    public Integer id;

    public List<OrderItem> items;

    public OrderPrice orderPrice;
}
