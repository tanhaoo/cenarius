package com.th.cenarius.commons.fee;

import com.th.cenarius.commons.constants.BaseEnum;
import com.th.cenarius.commons.pattern.fee.IFeeItemType;

/**
 * @Author: Aaron
 * @Date: 2023/6/8
 */
public enum FeeItemTypeEnum implements IFeeItemType {
    /**
     * Fee Item Type
     */
    FREIGHT_FEE(1, "运费"),
    TAX_FEE(2, "税费"),
    GOODS_FEE(3, "物品费"),
    OVER_TIME_FEE(4, "超时费"),
    ;
    private final Integer code;
    private final String name;

    FeeItemTypeEnum(Integer code, String name) {
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

    public static FeeItemTypeEnum of(Integer code) {
        return BaseEnum.parseByCode(FeeItemTypeEnum.class, code);
    }


}
