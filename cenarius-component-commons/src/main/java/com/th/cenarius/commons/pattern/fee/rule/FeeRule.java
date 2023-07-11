package com.th.cenarius.commons.pattern.fee.rule;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/6/8
 */
public interface FeeRule {

    BigDecimal getConfigValue();

    IFeeRuleType getRuleType();

    Integer getOrder();
}