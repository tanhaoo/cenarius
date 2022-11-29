package com.th.test.strategy.config;

import com.th.cenarius.web.common.StrategyFactory;
import com.th.test.strategy.paycallback.PayCallbackStrategy;
import com.th.test.strategy.paymenttype.PaymentTypeStrategy;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
@Component
public class StrategyFactoryConfig {

    @Bean
    public StrategyFactory<String, PayCallbackStrategy> getPayCallbackFactory() {
        return new StrategyFactory<>(PayCallbackStrategy.class);
    }

    @Bean
    public StrategyFactory<String, PaymentTypeStrategy> getPaymentTypeFactory() {
        return new StrategyFactory<>(PaymentTypeStrategy.class);
    }
}
