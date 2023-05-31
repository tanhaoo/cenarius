package com.th.cola.domain.product.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    private String spuCode;

    private String skuCode;

    private BigDecimal unitPrice;

    private BigDecimal discountUnitPrice;

}
