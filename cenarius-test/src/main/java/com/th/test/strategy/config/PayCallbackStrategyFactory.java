package com.th.test.strategy.config;

import com.th.cenarius.web.common.AbstractStrategyFactory;
import com.th.test.strategy.paycallback.PayCallbackStrategy;

import org.springframework.stereotype.Component;

/**
 * @Author: Aaron
 * @Date: 2022/11/29
 */
@Component
public class PayCallbackStrategyFactory extends AbstractStrategyFactory<String, PayCallbackStrategy> {
}
