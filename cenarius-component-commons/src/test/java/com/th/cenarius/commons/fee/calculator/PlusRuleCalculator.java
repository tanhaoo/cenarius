package com.th.cenarius.commons.fee.calculator;

import com.th.cenarius.commons.Order;
import com.th.cenarius.commons.fee.AbstractCalculator;
import com.th.cenarius.commons.fee.FeeCalculate;
import com.th.cenarius.commons.fee.FeeItemType;
import com.th.cenarius.commons.fee.Unique;
import com.th.cenarius.commons.pay.PayItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author: Aaron
 * @Date: 2023/6/12
 */
public class PlusRuleCalculator extends AbstractCalculator<Order> {

    private final BigDecimal discountRate;

    public PlusRuleCalculator(FeeCalculate<Order> feeCalculate, Unique unique, BigDecimal discount) {
        super(feeCalculate, unique);
        this.discountRate = discount;
    }

    @Override
    public Map<FeeItemType, List<PayItem>> payItemList() {
        return null;
    }

    @Override
    public Map<FeeItemType, BigDecimal> currentPayItem(Map<FeeItemType, BigDecimal> items, Order context) {
        return null;
    }
}
