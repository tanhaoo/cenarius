package com.th.cenarius.commons.pay;

import com.th.cenarius.commons.constants.BaseEnum;

/**
 * @Author: Aaron
 * @Date: 2023/6/20
 */
public enum PayGroup implements BaseEnum {
    /**
     * Pay Group
     */
    PLATFORM_PAY(1, "Platform Pay"),
    THIRD_PAY(2, "Third Pay"),
    CREDIT_CARD(3, "Credit Card");

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

    public static PayGroup of(Integer code) {
        return BaseEnum.parseByCode(PayGroup.class, code);
    }
}
