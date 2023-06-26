package com.th.cenarius.commons.pay;

import com.th.cenarius.commons.constants.BaseEnum;

/**
 * @Author: Aaron
 * @Date: 2023/6/13
 */
public enum PayType implements BaseEnum {
    /**
     * Pay Type
     */
    JPMORGAN(1, "JP Morgan"),
    PAYPAL(2, "Pay Pal"),
    STRIPE(3, "Stripe"),
    POINTS(4, "Points");;
    private final Integer code;
    private final String name;

    PayType(Integer code, String name) {
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

    public static PayType of(Integer code) {
        return BaseEnum.parseByCode(PayType.class, code);
    }
}
