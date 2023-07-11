package com.th.cenarius.commons.pattern.fee;

import com.th.cenarius.commons.pattern.fee.rule.IFeeRuleType;
import com.th.cenarius.commons.pattern.fee.pay.IPayItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author: Aaron
 * @Date: 2023/6/8
 */
public interface FeeCalculate<O> {

    Map<IFeeItemType, List<IPayItem>> payItemList(List<FeeItem<O>> items);

    Map<IFeeItemType, BigDecimal> calculateWaitPay(List<FeeItem<O>> items);

    IFeeRuleType getFeeRuleType();

}
