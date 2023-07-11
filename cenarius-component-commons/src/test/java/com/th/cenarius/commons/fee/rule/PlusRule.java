package com.th.cenarius.commons.fee.rule;

import com.th.cenarius.commons.pattern.fee.rule.AbstractFeeRule;
import com.th.cenarius.commons.pattern.fee.rule.IFeeRuleType;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Aaron
 * @Date: 2023/6/26
 */
public class PlusRule extends AbstractFeeRule {

    @Getter
    @Setter
    private String userId;

    public PlusRule(BigDecimal configValue, IFeeRuleType ruleType, Integer order) {
        super(configValue, ruleType, order);
    }
}
