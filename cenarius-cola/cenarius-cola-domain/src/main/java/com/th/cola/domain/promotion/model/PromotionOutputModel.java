package com.th.cola.domain.promotion.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionOutputModel {

    private List<Promotion> promotions = new ArrayList<>();

    private List<PromotionHints> hints = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private Set<FreeGift> freeGifts = new HashSet<>();

//    private MonetaryValue totalAmount;
//
//    private MonetaryValue freightAmount;
//
//    private MonetaryValue promoDiscountAmount;

    private BigDecimal discountRate;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Product {
        private String spuCode;
        private String skuCode;
        private BigDecimal totalAmount;
    }
}
