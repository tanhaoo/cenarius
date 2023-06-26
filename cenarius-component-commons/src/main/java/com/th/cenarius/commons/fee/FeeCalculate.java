package com.th.cenarius.commons.fee;

import com.th.cenarius.commons.pay.PayItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author: Aaron
 * @Date: 2023/6/8
 */
public interface FeeCalculate<O> {

    Map<FeeItemType, List<PayItem>> payItemList(List<FeeItem<O>> items);

    Map<FeeItemType, BigDecimal> calculateWaitPay(List<FeeItem<O>> items);

    Unique getUnique();

}
