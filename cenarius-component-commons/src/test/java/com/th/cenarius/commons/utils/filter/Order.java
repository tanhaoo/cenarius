package com.th.cenarius.commons.utils.filter;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2023/4/20
 */
@AllArgsConstructor
@Data
public class Order {
    String orderId;

    BigDecimal amount;

    String detail;
}
