package com.th.test.pattern.strategy.config;

import com.th.cenarius.web.common.strategy.StrategyFactory;
import com.th.test.pattern.strategy.paycallback.PayCallbackStrategy;
import com.th.test.pattern.strategy.paymenttype.PaymentTypeStrategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
@Configuration
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
