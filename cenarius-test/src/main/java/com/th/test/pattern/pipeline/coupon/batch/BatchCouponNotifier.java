package com.th.test.pattern.pipeline.coupon.batch;

import com.th.cenarius.web.common.pipeline.ContextHandler;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/12/1
 */
@Slf4j
@Component
public class BatchCouponNotifier implements ContextHandler<BatchCouponContext> {
    @Override
    public boolean handle(BatchCouponContext context) {
        log.info("[{}] Start notice, data: {}", context.getSimpleName(), context.getCouponCodes());
        return true;
    }
}
