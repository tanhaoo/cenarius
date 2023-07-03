package com.th.cenarius.commons.fee.payItem;

import com.th.cenarius.commons.fee.pay.AbstractPayItem;
import com.th.cenarius.commons.fee.pay.IPayGroup;
import com.th.cenarius.commons.fee.pay.IPayType;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Aaron
 * @Date: 2023/6/28
 */
public class PlusPayItem extends AbstractPayItem {

    @Setter
    @Getter
    private String userId;

    public PlusPayItem(BigDecimal money, IPayType payType, IPayGroup payGroup) {
        super(money, payType, payGroup);
    }
}
