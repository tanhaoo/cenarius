package com.th.cenarius.commons.pattern.pipeline;

import com.th.cenarius.commons.constants.BaseEnum;
import com.th.cenarius.commons.model.IBizEnum;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
public enum BizEnum implements IBizEnum {
    /**
     * Business Code
     */
    BIZ_ORDER(1, "Create order"),
    BIZ_COMMONS(2, "Common Biz");
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
