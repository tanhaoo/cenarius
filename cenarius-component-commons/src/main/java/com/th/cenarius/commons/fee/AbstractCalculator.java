package com.th.cenarius.commons.fee;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.th.cenarius.commons.pay.PayItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.RequiredArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/6/12
 */
@RequiredArgsConstructor
public abstract class AbstractCalculator<O> implements FeeCalculate<O> {

    private final FeeCalculate<O> feeCalculate;

    private final Unique unique;

    public abstract Map<FeeItemType, List<PayItem>> payItemList();

    public abstract Map<FeeItemType, BigDecimal> currentPayItem(Map<FeeItemType, BigDecimal> items, O context);

    @Override
    public Map<FeeItemType, List<PayItem>> payItemList(List<FeeItem<O>> items) {
        Map<FeeItemType, List<PayItem>> map;
        if (Objects.nonNull(this.feeCalculate)) {
            map = this.feeCalculate.payItemList(items);
        } else {
            map = Maps.newHashMap();
        }

        Map<FeeItemType, List<PayItem>> currentList = this.payItemList();
        currentList.forEach((key, value) -> {
            List<PayItem> payItems = map.getOrDefault(key, Lists.newArrayList());
            payItems.addAll(value);
            map.put(key, payItems);
        });

        return map;
    }

    @Override
    public Map<FeeItemType, BigDecimal> calculateWaitPay(List<FeeItem<O>> items) {
        return null;
    }

    @Override
    public Unique getUnique() {
        return this.unique;
    }
}
