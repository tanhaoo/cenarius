package com.th.cenarius.commons.fee.calculator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.th.cenarius.commons.Order;
import com.th.cenarius.commons.pattern.fee.AbstractCalculator;
import com.th.cenarius.commons.pattern.fee.FeeCalculate;
import com.th.cenarius.commons.fee.FeeItemTypeEnum;
import com.th.cenarius.commons.pattern.fee.IFeeItemType;
import com.th.cenarius.commons.pattern.fee.pay.IPayItem;
import com.th.cenarius.commons.fee.pay.PayGroup;
import com.th.cenarius.commons.fee.pay.PayType;
import com.th.cenarius.commons.fee.payItem.MaxLimitPayItem;
import com.th.cenarius.commons.pattern.fee.rule.FeeRule;
import com.th.cenarius.commons.pattern.fee.rule.IFeeRuleType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: Aaron
 * @Date: 2023/6/12
 */
public class MaxLimitCalculator extends AbstractCalculator<Order> {

    private final BigDecimal maxAmount;

    private Optional<BigDecimal> limitDeductAmount = Optional.empty();

    public MaxLimitCalculator(FeeCalculate<Order> feeCalculate, IFeeRuleType ruleType, BigDecimal maxAmount) {
        super(feeCalculate, ruleType);
        this.maxAmount = maxAmount;
    }

    @Override
    public Map<IFeeItemType, List<IPayItem>> payItemList() {
        Map<IFeeItemType, List<IPayItem>> maps = Maps.newHashMap();

        if (limitDeductAmount.isPresent()) {
            List<IPayItem> list = Lists.newArrayList();

            MaxLimitPayItem maxLimitPayItem = new MaxLimitPayItem(limitDeductAmount.get(), PayType.POINTS, PayGroup.CAMPAIGN);
            list.add(maxLimitPayItem);
            maps.put(FeeItemTypeEnum.GOODS_FEE, list);
        }
        return maps;
    }

    @Override
    public Map<IFeeItemType, BigDecimal> currentPayItem(Map<IFeeItemType, BigDecimal> previous, Order context) {
        Map<IFeeItemType, BigDecimal> maps = Maps.newHashMap();

        BigDecimal previousFee = previous.get(FeeItemTypeEnum.GOODS_FEE);
        if (previousFee.compareTo(maxAmount) > 0) {
            BigDecimal exceedAmount = previousFee.subtract(maxAmount);
            maps.put(FeeItemTypeEnum.GOODS_FEE, exceedAmount);
            this.limitDeductAmount = Optional.of(exceedAmount);
        }

        return maps;
    }

    @Override
    public FeeCalculate getCalculator(FeeCalculate calculate, FeeRule rule) {
        return new MaxLimitCalculator(calculate, rule.getRuleType(), rule.getConfigValue());
    }
}
