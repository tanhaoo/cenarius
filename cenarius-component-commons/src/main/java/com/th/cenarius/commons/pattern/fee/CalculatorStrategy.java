package com.th.cenarius.commons.pattern.fee;

import com.th.cenarius.commons.pattern.fee.rule.FeeRule;
import com.th.cenarius.commons.pattern.fee.rule.IFeeRuleType;
import com.th.cenarius.commons.pattern.strategy.Strategy;

/**
 * @Author: Aaron
 * @Date: 2023/6/26
 */
public interface CalculatorStrategy extends Strategy<IFeeRuleType> {
    @Override
    IFeeRuleType getId();

    FeeCalculate getCalculator(FeeCalculate calculate, FeeRule rule);

}
