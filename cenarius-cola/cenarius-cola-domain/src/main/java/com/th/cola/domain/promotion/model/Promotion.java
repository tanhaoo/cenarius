package com.th.cola.domain.promotion.model;


import com.th.cola.domain.product.model.Product;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Author: Aaron
 * @Date: 2022/10/27
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    private PromotionMethod method;

    private PromotionTarget target;

    private PromotionRule rule;

    private BigDecimal discountAmount;

    private BigDecimal discountRate;

    private String redeemCode;

    private String couponCode;

    private Set<Product> relatedSkus;

    // 如果是优惠作用在 item 级别，要把他们都列出来
    // promotion items;

    /**
     * "promotions": [{
     * "type": "coupons",
     * "target": "",
     * "promoTarget": "ItemTotalAmount",
     * "price": 30.00 ,
     * "items":[{
     * "skuCode":"2729789"
     * <p>
     * }],
     * }, {
     * "type": "coupons",
     * "":
     * }]
     */

    public Promotion(PromotionTarget target, BigDecimal discountAmount, String redeemCode) {
        this.target = target;
        this.discountAmount = discountAmount;
        this.redeemCode = redeemCode;
    }

    public Promotion(String couponCode) {
        this.couponCode = couponCode;
    }


}
