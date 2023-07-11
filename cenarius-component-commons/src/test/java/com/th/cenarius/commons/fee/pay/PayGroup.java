package com.th.cenarius.commons.fee.pay;

import com.th.cenarius.commons.constants.BaseEnum;
import com.th.cenarius.commons.pattern.fee.pay.IPayGroup;

/**
 * @Author: Aaron
 * @Date: 2023/6/20
 */
public enum PayGroup implements IPayGroup {
    /**
     * Pay Group
     */
    PLATFORM_PAY(1, "Platform Pay"),
    THIRD_PAY(2, "Third Pay"),
    CREDIT_CARD(3, "Credit Card"),
    CAMPAIGN(4, "Campaign"),
    COUPON(5, "Coupon"),
    VIRTUAL_PROPERTY(3, "Virtual property"),
    ;

    private final Integer code;
    private final String name;

    PayGroup(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    public static IPayGroup of(Integer code) {
        return BaseEnum.parseByCode(PayGroup.class, code);
    }
}
