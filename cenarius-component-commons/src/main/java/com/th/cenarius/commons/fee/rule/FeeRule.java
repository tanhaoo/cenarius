package com.th.cenarius.commons.fee.rule;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/6/8
 */
public interface FeeRule {

    BigDecimal getConfigValue();

    FeeRuleType getRuleType();

    Integer getOrder();
}
