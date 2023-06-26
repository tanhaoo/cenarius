package com.th.cenarius.commons;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Author: Aaron
 * @Date: 2023/4/20
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class Order {
    private String orderId;

    private BigDecimal amount;

    private String detail;

    private String userId;

    private BigDecimal orderItemFee;

    private BigDecimal taxFee;

    private BigDecimal freightFee;

    public Order(String orderId, BigDecimal amount, String detail) {
        this.orderId = orderId;
        this.amount = amount;
        this.detail = detail;
    }
}
