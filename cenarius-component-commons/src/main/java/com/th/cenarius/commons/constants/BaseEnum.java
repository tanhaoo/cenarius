package com.th.cenarius.commons.constants;

import java.util.Arrays;

/**
 * @Author: Aaron
 * @Date: 2022/9/13
 */
public interface BaseEnum {
    /**
     * Get Enum Code
     *
     * @return code
     */
    Integer getCode();

    /**
     * Get Enum Name
     *
     * @return name
     */
    String getName();

    /**
     * Get Enum By Code
     *
     * @param cls  Enum Class
     * @param code Enum Code
     * @param <T>  Enum
     * @return Enum
     */
    static <T extends BaseEnum> T parseByCode(Class<T> cls, Integer code) {
        T[] enums = cls.getEnumConstants();
        return Arrays.stream(enums)
                .filter(baseEnum -> baseEnum.getCode().equals(code))
                .findFirst()
                .orElseThrow(() ->
                        new UnsupportedOperationException("The code " + code + " in " + cls.getName() + " is not supported!"));
    }
}
