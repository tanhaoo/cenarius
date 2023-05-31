package com.th.cola.infra.promotion.gateway;

import com.th.cola.domain.promotion.gateway.IPromotionGateway;
import com.th.cola.domain.promotion.model.PromotionInputModel;
import com.th.cola.domain.promotion.model.PromotionOutputModel;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/25
 */
@Component
@Slf4j
public class PromotionGatewayImpl implements IPromotionGateway {
    @Override
    public PromotionOutputModel calculate(boolean isUseCoupon, boolean isUseCampaign, String redeemCode, String couponCode, PromotionInputModel inputModel) {
        return null;
    }

    @Override
    public boolean useCoupon() {
        log.info("The coupon has been marked as used");
        return false;
    }
}
