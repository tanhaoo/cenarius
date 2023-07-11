package com.th.cenarius.commons.fee.payItem;

import com.th.cenarius.commons.pattern.fee.pay.AbstractPayItem;
import com.th.cenarius.commons.pattern.fee.pay.IPayGroup;
import com.th.cenarius.commons.pattern.fee.pay.IPayType;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/6/28
 */
public class MaxLimitPayItem extends AbstractPayItem {

    public MaxLimitPayItem(BigDecimal money, IPayType payType, IPayGroup payGroup) {
        super(money, payType, payGroup);
    }
}
