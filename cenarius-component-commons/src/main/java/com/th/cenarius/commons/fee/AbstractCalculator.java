package com.th.cenarius.commons.fee;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.th.cenarius.commons.fee.pay.IPayItem;
import com.th.cenarius.commons.fee.rule.IFeeRuleType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/6/12
 */
@RequiredArgsConstructor
public abstract class AbstractCalculator<O> implements FeeCalculate<O>, CalculatorStrategy {

    private final FeeCalculate<O> feeCalculate;

    private final IFeeRuleType ruleType;

    public abstract Map<IFeeItemType, List<IPayItem>> payItemList();

    public abstract Map<IFeeItemType, BigDecimal> currentPayItem(Map<IFeeItemType, BigDecimal> previous, O context);


    @Override
    public Map<IFeeItemType, List<IPayItem>> payItemList(List<FeeItem<O>> items) {
        Map<IFeeItemType, List<IPayItem>> map;
        if (Objects.nonNull(this.feeCalculate)) {
            map = this.feeCalculate.payItemList(items);
        } else {
            map = Maps.newHashMap();
        }

        Map<IFeeItemType, List<IPayItem>> currentList = this.payItemList();
        currentList.forEach((key, value) -> {
            List<IPayItem> payItems = map.getOrDefault(key, Lists.newArrayList());
            payItems.addAll(value);
            map.put(key, payItems);
        });

        return map;
    }

    @Override
    public Map<IFeeItemType, BigDecimal> calculateWaitPay(List<FeeItem<O>> items) {
        assert items != null;

        Map<IFeeItemType, BigDecimal> tempMap = Maps.newHashMap();
        Map<IFeeItemType, BigDecimal> previousMap;

        if (Objects.nonNull(this.feeCalculate)) {
            previousMap = this.feeCalculate.calculateWaitPay(items);

            Optional<BigDecimal> greaterThanZero = previousMap
                    .values().stream()
                    .filter(value -> value.compareTo(BigDecimal.ZERO) > 0)
                    .findFirst();

            if (greaterThanZero.isEmpty()) {
                return previousMap;
            }

            Map<IFeeItemType, BigDecimal> currentPayItem = this.currentPayItem(previousMap, items.stream().findFirst().get().getOrderInfo());

            items.forEach(item -> {
                BigDecimal currentDiscount = currentPayItem.get(item.getFeeItemType());
                BigDecimal previousPay = previousMap.get(item.getFeeItemType());
                if (Objects.nonNull(currentDiscount)) {
                    tempMap.put(item.getFeeItemType(), previousPay.subtract(currentDiscount));
                } else {
                    tempMap.put(item.getFeeItemType(), previousPay);
                }
            });
            return tempMap;
        } else {
            items.forEach(item -> tempMap.put(item.getFeeItemType(), item.getFeeItemOriginMoney()));
            Map<IFeeItemType, BigDecimal> currentPayItem = this.currentPayItem(tempMap, items.stream().findFirst().get().getOrderInfo());
            currentPayItem.forEach((key, value) -> tempMap.put(key, tempMap.get(key).subtract(value)));
            return tempMap;
        }
    }

    @Override
    public IFeeRuleType getFeeRuleType() {
        return ruleType;
    }

    @Override
    public IFeeRuleType getId() {
        return getFeeRuleType();
    }
}
