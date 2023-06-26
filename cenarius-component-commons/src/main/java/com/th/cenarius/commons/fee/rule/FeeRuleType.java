package com.th.cenarius.commons.fee.rule;

import com.th.cenarius.commons.constants.BaseEnum;

import java.util.Optional;

/**
 * @Author: Aaron
 * @Date: 2023/6/8
 */
public enum FeeRuleType implements BaseEnum {
    /**
     * Fee Rule Type
     */
    PLUS_RULE(1, "plus会员规则"),
    MAX_LIMIT(2, "限额规则");

    FeeRuleType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static Optional<FeeRuleType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(FeeRuleType.class, code));
    }
}
