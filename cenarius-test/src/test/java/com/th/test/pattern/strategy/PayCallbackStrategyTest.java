package com.th.test.pattern.strategy;

import com.th.cenarius.web.common.strategy.StrategyFactory;
import com.th.test.pattern.strategy.config.StrategyFactoryConfig;
import com.th.test.pattern.strategy.paycallback.PayCallbackStrategy;
import com.th.test.pattern.strategy.paycallback.impl.PayFailureStrategy;
import com.th.test.pattern.strategy.paycallback.impl.PaySuccessStrategy;

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
@ContextConfiguration(classes = {StrategyFactoryConfig.class, PaySuccessStrategy.class, PayFailureStrategy.class})
public class PayCallbackStrategyTest {

    @Resource
    private StrategyFactory<String, PayCallbackStrategy> factory;

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
