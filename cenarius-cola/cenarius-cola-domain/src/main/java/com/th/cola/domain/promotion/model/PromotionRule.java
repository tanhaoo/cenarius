package com.th.cola.domain.promotion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xiaofei.x.xu@henkel.com
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionRule {

    private String id;

    private String name;

}
