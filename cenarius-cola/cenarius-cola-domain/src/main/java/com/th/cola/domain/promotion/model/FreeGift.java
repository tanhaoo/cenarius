package com.th.cola.domain.promotion.model;



import com.th.cola.domain.product.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaofei.x.xu@henkel.com
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "product")
public class FreeGift {

    private Product product;

    private PromotionRule rule;

    private Integer quantity;

}
