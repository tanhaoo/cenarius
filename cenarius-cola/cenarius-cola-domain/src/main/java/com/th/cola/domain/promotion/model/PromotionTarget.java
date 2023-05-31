package com.th.cola.domain.promotion.model;

import lombok.Getter;

/**
 * @author Aaron
 */
public enum PromotionTarget {

    /**
     * Total amount of the sales order
     */
    OrderTotalAmount(1),

    /**
     * Unit price of item
     */
    UnitPrice(2),

    /**
     * Total amount of specified product
     */
    ProductTotalAmount(3),

    /**
     * Total amount of all items
     */
    ItemTotalAmount(4),

    /**
     * FreightFee
     */
    FreightFee(5),

    /**
     * Free gift
     */
    FreeGift(6);

    @Getter
    private final int value;

    PromotionTarget(int value) {
        this.value = value;
    }

    public static PromotionTarget from(int value) {

        for (PromotionTarget target : PromotionTarget.values()) {
            if (target.getValue() == value) {
                return target;
            }
        }

        throw new UnsupportedOperationException(
                "The value " + value + " is not supported!");
    }
}
