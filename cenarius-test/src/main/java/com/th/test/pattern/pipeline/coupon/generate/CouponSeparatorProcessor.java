package com.th.test.pattern.pipeline.coupon.generate;

import com.th.cenarius.web.common.pipeline.ContextHandler;

import org.springframework.stereotype.Component;

/**
 * @Author: Aaron
 * @Date: 2022/11/25
 */
@Component
public class CouponSeparatorProcessor implements ContextHandler<CouponBuildContext> {

    @Override
    public boolean handle(CouponBuildContext context) {
        context.getCurCodeBuilder().append(context.getSeparator());
        return true;
    }
}
