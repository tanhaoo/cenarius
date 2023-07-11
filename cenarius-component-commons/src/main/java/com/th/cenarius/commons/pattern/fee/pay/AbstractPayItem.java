package com.th.cenarius.commons.pattern.fee.pay;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/6/28
 */
@RequiredArgsConstructor
public abstract class AbstractPayItem implements IPayItem {

    private final BigDecimal money;

    private final IPayType payType;

    private final IPayGroup payGroup;

    public BigDecimal getMoney() {
        return this.money;
    }

    public IPayGroup getPayGroup() {
        return this.payGroup;
    }

    public IPayType getPayType() {
        return this.payType;
    }
}
