package com.th.cenarius.commons.pattern.fee.rule;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/6/20
 */
@RequiredArgsConstructor
public class AbstractFeeRule implements FeeRule {

    private final BigDecimal configValue;

    private final IFeeRuleType ruleType;

    private final Integer order;

    @Override
    public BigDecimal getConfigValue() {
        return configValue;
    }

    @Override
    public IFeeRuleType getRuleType() {
        return ruleType;
    }

    @Override
    public Integer getOrder() {
        return order;
    }
}
