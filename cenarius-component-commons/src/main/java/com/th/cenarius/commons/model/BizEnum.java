package com.th.cenarius.commons.model;

import com.th.cenarius.commons.constants.BaseEnum;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
public enum BizEnum implements BaseEnum {
    /**
     * Business Code
     */
    BIZ_ORDER(1, "create order"),
    BIZ_2(2, "业务线2");
    private final Integer code;
    private final String name;

    BizEnum(Integer code, String name) {
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

    public static BizEnum of(Integer code) {
        return BaseEnum.parseByCode(BizEnum.class, code);
    }
}
