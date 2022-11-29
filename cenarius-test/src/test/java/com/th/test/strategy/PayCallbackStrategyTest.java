package com.th.test.strategy;

import com.th.test.strategy.config.PayCallbackStrategyFactory;
import com.th.test.strategy.paycallback.PayCallbackStrategy;
import com.th.test.strategy.paycallback.impl.PayFailureStrategy;
import com.th.test.strategy.paycallback.impl.PaySuccessStrategy;

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
@ContextConfiguration(classes = { PayCallbackStrategyFactory.class,PaySuccessStrategy.class, PayFailureStrategy.class})
public class PayCallbackStrategyTest {

    @Resource
    private PayCallbackStrategyFactory factory;

    @Test
    public void testSuccessStrategy() {
        PayCallbackStrategy success = factory.getStrategy("success");
        success.payCallback();
    }

    @Test
    public void testFailureStrategy() {
        PayCallbackStrategy failure = factory.getStrategy("failure");
        failure.payCallback();
    }
}
