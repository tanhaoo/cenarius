package com.th.test.pattern.strategy.config;

import com.th.cenarius.web.common.strategy.StrategyFactory;
import com.th.test.pattern.strategy.paycallback.PayCallbackStrategy;
import com.th.test.pattern.strategy.paymenttype.PaymentTypeStrategy;
import com.th.test.pattern.template.FormItemConverter;
import com.th.test.pattern.template.FormItemTypeEnum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
@Configuration
public class StrategyFactoryConfig {

    @Bean
    public StrategyFactory<String, PayCallbackStrategy> payCallbackFactory() {
        return new StrategyFactory<>(PayCallbackStrategy.class);
    }

    @Bean
    public StrategyFactory<String, PaymentTypeStrategy> paymentTypeFactory() {
        return new StrategyFactory<>(PaymentTypeStrategy.class);
    }

    @Bean
    public StrategyFactory<FormItemTypeEnum, FormItemConverter> formItemConverter() {
        return new StrategyFactory<>(FormItemConverter.class);
    }
}
