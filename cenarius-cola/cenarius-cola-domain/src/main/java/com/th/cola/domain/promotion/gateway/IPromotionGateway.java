package com.th.cola.domain.promotion.gateway;

import com.th.cola.domain.promotion.model.PromotionInputModel;
import com.th.cola.domain.promotion.model.PromotionOutputModel;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
public interface IPromotionGateway {

    PromotionOutputModel calculate(boolean isUseCoupon, boolean isUseCampaign, String redeemCode, String couponCode, PromotionInputModel inputModel);

    boolean useCoupon();
}
