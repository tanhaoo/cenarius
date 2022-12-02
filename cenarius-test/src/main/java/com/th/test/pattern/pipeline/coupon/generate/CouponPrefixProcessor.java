package com.th.test.pattern.pipeline.coupon.generate;

import com.th.cenarius.web.common.pipeline.ContextHandler;

import org.springframework.stereotype.Component;

/**
 * @Author: Aaron
 * @Date: 2022/11/24
 */
@Component
public class CouponPrefixProcessor implements ContextHandler<CouponBuildContext> {

    @Override
    public boolean handle(CouponBuildContext context) {
        context.getCurCodeBuilder().append(context.getPrefix());
        return true;
    }
}
