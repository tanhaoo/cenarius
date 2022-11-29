package com.th.test.strategy;

import com.th.cenarius.web.common.StrategyFactory;
import com.th.test.strategy.config.StrategyFactoryConfig;
import com.th.test.strategy.paymenttype.PaymentTypeStrategy;
import com.th.test.strategy.paymenttype.impl.PayByCheckoutStrategy;
import com.th.test.strategy.paymenttype.impl.PayByStripeStrategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {StrategyFactoryConfig.class, PayByStripeStrategy.class, PayByCheckoutStrategy.class})
public class PaymentTypeStrategyTest {

    @Resource
    private StrategyFactory<String, PaymentTypeStrategy> factory;

    @Test
    public void testSuccessStrategy() {
        PaymentTypeStrategy strategy = factory.getStrategy("Stripe");
        strategy.doPay();
    }

    @Test
    public void testFailureStrategy() {
        PaymentTypeStrategy strategy = factory.getStrategy("Checkout");
        strategy.doPay();
    }
}