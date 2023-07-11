package com.th.cenarius.commons.fee;

import com.th.cenarius.commons.Order;
import com.th.cenarius.commons.pattern.fee.FeeItem;
import com.th.cenarius.commons.pattern.fee.IFeeItemType;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/6/26
 */
@RequiredArgsConstructor
public class OrderFeeItem implements FeeItem<Order> {

    private final Order order;

    @Override
    public BigDecimal getFeeItemOriginMoney() {
        return order.getOrderItemFee();
    }

    @Override
    public IFeeItemType getFeeItemType() {
        return FeeItemTypeEnum.GOODS_FEE;
    }

    @Override
    public Order getOrderInfo() {
        return order;
    }
}
