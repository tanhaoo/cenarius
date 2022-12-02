package com.th.test.pattern.pipeline.config;

import com.th.cenarius.web.common.pipeline.ContextHandler;
import com.th.cenarius.web.common.pipeline.PipelineContext;
import com.th.cenarius.web.common.pipeline.PipelineExecutor;
import com.th.test.pattern.pipeline.coupon.batch.BatchCouponContext;
import com.th.test.pattern.pipeline.coupon.batch.BatchCouponCreator;
import com.th.test.pattern.pipeline.coupon.batch.BatchCouponNotifier;
import com.th.test.pattern.pipeline.coupon.batch.BatchCouponSaver;
import com.th.test.pattern.pipeline.coupon.batch.BatchCouponValidator;
import com.th.test.pattern.pipeline.coupon.generate.CouponBuildContext;
import com.th.test.pattern.pipeline.coupon.generate.CouponInstanceCreator;
import com.th.test.pattern.pipeline.coupon.generate.CouponPrefixProcessor;
import com.th.test.pattern.pipeline.coupon.generate.CouponSeparatorProcessor;
import com.th.test.pattern.pipeline.coupon.generate.CouponSuffixProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Aaron
 * @Date: 2022/12/1
 */
@Configuration
public class PipelineRouteConfig {

    HashMap<Class<? extends PipelineContext>, List<Class<? extends ContextHandler<? extends PipelineContext>>>> maps = new HashMap<>();

    @Bean("couponExecutor")
    public PipelineExecutor getBatchCouponPipelineExecutor() {
        setBatchCouponPipeline();
        setGenerateCouponPipeline();
        return new PipelineExecutor(maps);
    }

    /**
     * Generate a coupon by customized rules
     */
    public void setGenerateCouponPipeline() {
        // Current rule: prefix + separator + generate coupon + separator + suffix
        maps.put(CouponBuildContext.class,
                Arrays.asList(
                        CouponPrefixProcessor.class,
                        CouponSeparatorProcessor.class,
                        CouponInstanceCreator.class,
                        CouponSeparatorProcessor.class,
                        CouponSuffixProcessor.class
                ));
    }

    public void setBatchCouponPipeline() {
        maps.put(BatchCouponContext.class,
                Arrays.asList(
                        BatchCouponValidator.class,
                        BatchCouponCreator.class,
                        BatchCouponSaver.class,
                        BatchCouponNotifier.class
                ));
    }


}
