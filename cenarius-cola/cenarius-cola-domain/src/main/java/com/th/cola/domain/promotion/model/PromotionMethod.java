package com.th.cola.domain.promotion.model;

import lombok.Getter;

/**
 * @author Aaron
 */
public enum PromotionMethod {

    /**
     * Coupon
     */
    Coupon(1),

    /**
     *
     */
    Campaign(2);

    @Getter
    private final int value;

    PromotionMethod(int value) {
        this.value = value;
    }

    public static PromotionMethod from(int value) {

        for (PromotionMethod method : PromotionMethod.values()) {
            if (method.getValue() == value) {
                return method;
            }
        }

        throw new UnsupportedOperationException(
                "The value " + value + " is not supported!");
    }

    public static PromotionMethod from(String value) {

        for (PromotionMethod method : PromotionMethod.values()) {
            if (method.name().equalsIgnoreCase(value)) {
                return method;
            }
        }

        throw new UnsupportedOperationException(
                "The value " + value + " is not supported!");
    }

}
