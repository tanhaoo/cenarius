package com.th.test.pattern.strategy.paycallback.impl;

import com.th.test.pattern.strategy.paycallback.PayCallbackStrategy;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
@Slf4j
@Component
public class PayFailureStrategy implements PayCallbackStrategy {
    @Override
    public String getPaymentStatus() {
        return "failure";
    }

    @Override
    public void payCallback() {
        log.info("Receive callback: {}", getPaymentStatus());
    }
}
