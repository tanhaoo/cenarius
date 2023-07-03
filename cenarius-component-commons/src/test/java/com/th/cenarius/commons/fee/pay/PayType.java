package com.th.cenarius.commons.fee.pay;

import com.th.cenarius.commons.constants.BaseEnum;

/**
 * @Author: Aaron
 * @Date: 2023/6/13
 */
public enum PayType implements IPayType {
    /**
     * Pay Type
     */
    RED_PACKET(1, "Red Packet"),
    POINTS(4, "Points");
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

    public static IPayType of(Integer code) {
        return BaseEnum.parseByCode(PayType.class, code);
    }
}
