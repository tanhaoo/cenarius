package com.th.test.strategy.paycallback.impl;

import com.th.test.strategy.paycallback.PayCallbackStrategy;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
@Slf4j
@Component
public class PaySuccessStrategy implements PayCallbackStrategy {
    @Override
    public String getPaymentStatus() {
        return "success";
    }

    @Override
    public void payCallback() {
        log.info("Receive callback: {}", getPaymentStatus());
    }
}
