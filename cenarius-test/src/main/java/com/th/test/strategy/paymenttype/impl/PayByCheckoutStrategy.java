package com.th.test.strategy.paymenttype.impl;

import com.th.test.strategy.paymenttype.PaymentTypeStrategy;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
@Component
@Slf4j
public class PayByCheckoutStrategy implements PaymentTypeStrategy {
    @Override
    public String getPaymentType() {
        return "Checkout";
    }

    @Override
    public void doPay() {
        log.info("Start pay by: {}", getPaymentType());
    }
}
