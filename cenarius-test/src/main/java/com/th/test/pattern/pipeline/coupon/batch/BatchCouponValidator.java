package com.th.test.pattern.pipeline.coupon.batch;

import com.th.cenarius.web.common.pipeline.ContextHandler;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/12/1
 */
@Component
@Slf4j
public class BatchCouponValidator implements ContextHandler<BatchCouponContext> {
    @Override
    public boolean handle(BatchCouponContext context) {
        log.info("[{}] Start validation", context.getSimpleName());
        boolean condition = false;

        if (condition) {
            context.setErrorMsg("Validation failure");
            return false;
        }
        return true;
    }
}
