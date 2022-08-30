package com.th.test.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    public Integer id;

    public OrderPrice itemPrice;
}
