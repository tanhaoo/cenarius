package com.th.test.pattern.strategy.paymenttype;

import com.th.cenarius.web.common.strategy.Strategy;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
public interface PaymentTypeStrategy extends Strategy<String> {
    /**
     * Get payment type
     *
     * @return payment type
     */
    String getPaymentType();

    /**
     * Do pay
     */
    void doPay();

    /**
     * Get id of Payment Type
     *
     * @return ID
     */
    @Override
    default String getId() {
        return getPaymentType();
    }
}
