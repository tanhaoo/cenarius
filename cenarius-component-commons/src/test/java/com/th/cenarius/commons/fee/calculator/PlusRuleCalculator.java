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
import com.th.cenarius.commons.fee.payItem.PlusPayItem;
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
public class PlusRuleCalculator extends AbstractCalculator<Order> {

    private final BigDecimal discountRate;

    private Optional<BigDecimal> payMoney = Optional.empty();

    private Order order;

    public PlusRuleCalculator(FeeCalculate<Order> feeCalculate, IFeeRuleType ruleType, BigDecimal discountRate) {
        super(feeCalculate, ruleType);
        this.discountRate = discountRate;
    }

    @Override
    public Map<IFeeItemType, List<IPayItem>> payItemList() {
        Map<IFeeItemType, List<IPayItem>> maps = Maps.newHashMap();

        if (payMoney.isPresent()) {
            List<IPayItem> list = Lists.newArrayList();
            PlusPayItem plusPayItem = new PlusPayItem(payMoney.get(), PayType.RED_PACKET, PayGroup.VIRTUAL_PROPERTY);
            plusPayItem.setUserId(order.getUserId());
            list.add(plusPayItem);
            maps.put(FeeItemTypeEnum.GOODS_FEE, list);
        }

        return maps;
    }

    @Override
    public Map<IFeeItemType, BigDecimal> currentPayItem(Map<IFeeItemType, BigDecimal> previous, Order context) {
        Map<IFeeItemType, BigDecimal> maps = Maps.newHashMap();
        if (context.getUserPlus()) {
            order = context;
            BigDecimal previousAmount = previous.get(FeeItemTypeEnum.GOODS_FEE);
            if (previousAmount.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal discountAmount = previousAmount.multiply(discountRate);
                maps.put(FeeItemTypeEnum.GOODS_FEE, discountAmount);
                this.payMoney = Optional.of(discountAmount);
            }
        }

        return maps;
    }


    @Override
    public FeeCalculate getCalculator(FeeCalculate calculate, FeeRule rule) {
        return new PlusRuleCalculator(calculate, rule.getRuleType(), rule.getConfigValue());
    }
}
