package com.th.test.pattern.strategy.paycallback;

import com.th.cenarius.web.common.strategy.Strategy;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
public interface PayCallbackStrategy extends Strategy<String> {
    /**
     * Get payment status
     *
     * @return payment status
     */
    String getPaymentStatus();

    /**
     * Callback
     */
    void payCallback();

    /**
     * Get id of Pay Callback
     *
     * @return ID
     */
    @Override
    default String getId() {
        return getPaymentStatus();
    }
}
