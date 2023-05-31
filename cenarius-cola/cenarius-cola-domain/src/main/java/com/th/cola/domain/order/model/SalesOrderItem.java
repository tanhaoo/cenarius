package com.th.cola.domain.order.model;

import com.th.cola.domain.product.model.Product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderItem {

    private String itemId;

    private Product product;

    private Long qty;

    private BigDecimal originalPrice;

    private BigDecimal discountPrice;
}
