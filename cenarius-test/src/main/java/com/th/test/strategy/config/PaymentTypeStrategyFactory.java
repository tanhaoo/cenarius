package com.th.test.strategy.config;

import com.th.cenarius.web.common.AbstractStrategyFactory;
import com.th.test.strategy.paymenttype.PaymentTypeStrategy;

import org.springframework.stereotype.Component;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
@Component
public class PaymentTypeStrategyFactory extends AbstractStrategyFactory<String, PaymentTypeStrategy> {
}
