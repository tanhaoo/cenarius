package com.th.cenarius.commons.pattern.fee;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/6/8
 */
public interface FeeItem<O> {

    BigDecimal getFeeItemOriginMoney();

    IFeeItemType getFeeItemType();

    O getOrderInfo();
}
