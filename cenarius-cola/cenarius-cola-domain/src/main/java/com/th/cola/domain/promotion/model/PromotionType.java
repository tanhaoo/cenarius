package com.th.cola.domain.promotion.model;

import lombok.Getter;

/**
 * @author Aaron
 */
public enum PromotionType {

    /**
     * X off
     */
    Off(1),

    /**
     * X off over Y
     */
    OffOver(2),

    /**
     * percent off
     */
    PercentOff(3),

    /**
     * Buy X Get Y
     */
    BuyGet(4);

    @Getter
    private final int value;

    PromotionType(int value) {
        this.value = value;
    }

    public static PromotionType from(int value) {

        for (PromotionType type : PromotionType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        throw new UnsupportedOperationException(
                "The value " + value + " is not supported!");
    }
}
