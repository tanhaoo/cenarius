package com.th.test.pattern.pipeline.coupon.batch;

import com.th.cenarius.web.common.pipeline.ContextHandler;
import com.th.cenarius.web.common.pipeline.PipelineExecutor;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/12/1
 */
@Component
@Slf4j
public class BatchCouponCreator implements ContextHandler<BatchCouponContext> {

    @Resource(name = "couponExecutor")
    @Lazy
    private PipelineExecutor couponExecutor;

    @Override
    public boolean handle(BatchCouponContext context) {
        log.info("[{}] Start to create, current dictionary {}", context.getSimpleName(), context.getCouponContext().getDictionary());

        for (int i = 0; i < context.getQuantity(); i++) {

            double curIndex = context.getLastIndex() + i;
            context.getCouponContext().setCurIndex(curIndex);
            context.getCouponContext().setCurCodeBuilder(new StringBuilder());

            boolean result = couponExecutor.acceptSync(context.getCouponContext());
            if (!result) {
                return false;
            }

            context.getCouponCodes().add(context.getCouponContext().getCurCodeBuilder().toString());
        }

        return true;
    }
}
