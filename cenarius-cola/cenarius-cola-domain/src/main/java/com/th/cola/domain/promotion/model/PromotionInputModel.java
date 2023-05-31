package com.th.cola.domain.promotion.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Author: Aaron
 * @Date: 2023/5/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PromotionInputModel {

//    private AccountContext context;

    private String storeCode;

    private BigDecimal freightAmount;

//    private Currency currency;

    private List<Product> products = new ArrayList<>();


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Product {
        private String spuCode;
        private String skuCode;
        private BigDecimal price;
        private Integer quantity;
        private String brand;
        private List<String> categories;
    }
}
