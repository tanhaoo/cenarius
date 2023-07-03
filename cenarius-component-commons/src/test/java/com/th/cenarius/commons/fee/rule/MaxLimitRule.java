package com.th.cenarius.commons.fee.rule;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/6/26
 */
public class MaxLimitRule extends AbstractFeeRule {

    public MaxLimitRule(BigDecimal configValue, IFeeRuleType ruleType, Integer order) {
        super(configValue, ruleType, order);
    }
}
