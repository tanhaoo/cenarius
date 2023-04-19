package com.th.cenarius.commons.constants;

/**
 * @Author: Aaron
 * @Date: 2022/9/13
 */
public enum FeeTypeEnum implements BaseEnum {
    FREIGHT(1, "Freight"),
    TAX(2, "Tax"),
    SERVICE_FEE(3, "Service Fee"),

    ;

    private final Integer code;
    private final String name;

    FeeTypeEnum(Integer code, String name) {

        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static FeeTypeEnum of(Integer code) {
        return BaseEnum.parseByCode(FeeTypeEnum.class, code);
    }
}
