package com.th.test.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@AllArgsConstructor
@Data
public class OrderItem {
    public Integer id;

    public OrderPrice itemPrice;
}
