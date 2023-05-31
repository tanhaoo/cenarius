package com.th.cola.domain.promotion.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/1/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionHints {

    private String difference;

    private BigDecimal discountedValue;
}
