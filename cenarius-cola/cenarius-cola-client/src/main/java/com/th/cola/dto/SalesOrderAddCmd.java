package com.th.cola.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderAddCmd {

    private String description;

    private BigDecimal totalAmount;

    private String operateUser;

    private Boolean isUseCoupon;

    private List<SalesOrderItem> items;

    private SalesOrderSubmitCmdResult execResult = new SalesOrderSubmitCmdResult();

    public SalesOrderAddCmd(String description, BigDecimal totalAmount, String operateUser, Boolean isUseCoupon, List<SalesOrderItem> items) {
        this.description = description;
        this.totalAmount = totalAmount;
        this.operateUser = operateUser;
        this.isUseCoupon = isUseCoupon;
        this.items = items;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SalesOrderItem {

        private String itemId;

        private Product product;

        private Long qty;

        private BigDecimal originalPrice;

        private BigDecimal discountPrice;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Product {
        private String spuCode;

        private String skuCode;

        private BigDecimal unitPrice;

        private BigDecimal discountUnitPrice;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SalesOrderSubmitCmdResult {

        //        @Schema(description = "Identity of sales order.", example = "109594362665033728")
        private String id;

    }
}
