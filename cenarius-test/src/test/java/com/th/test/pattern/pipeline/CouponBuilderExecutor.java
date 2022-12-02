package com.th.test.pattern.pipeline;

import com.th.cenarius.web.common.pipeline.PipelineExecutor;
import com.th.test.pattern.pipeline.config.PipelineRouteConfig;
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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2022/12/1
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PipelineRouteConfig.class, CouponInstanceCreator.class, CouponPrefixProcessor.class, CouponSeparatorProcessor.class, CouponSuffixProcessor.class,
        BatchCouponValidator.class, BatchCouponCreator.class, BatchCouponSaver.class, BatchCouponNotifier.class,
        ThreadPoolTaskExecutor.class
})
public class CouponBuilderExecutor {

    @Resource(name = "couponExecutor")
    private PipelineExecutor couponExecutor;

    @Test
    public void testCouponExecute() {
        CouponBuildContext couponContext = CouponBuildContext.builder()
                .partCount(2).partLen(3)
                .prefix("PRE").suffix("SUF")
                .separator("-")
                .dictionary(baseDictString()).build();

        BatchCouponContext batchCouponContext = BatchCouponContext.builder().couponContext(couponContext).quantity(10).lastIndex(1.0).build();

//        boolean result = couponExecutor.acceptSync(batchCouponContext);

//        if (!result){
//            log.error("[{}] occur error，error meesage={}", batchCouponContext.getSimpleName(), batchCouponContext.getErrorMsg());
//        }

        couponExecutor.acceptAsync(batchCouponContext, (pipeline, res) -> System.out.println(pipeline + " " + res));
    }

    private String baseDictString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            // 0-9
            builder.append(i);
        }
        for (int i = 65; i <= 90; i++) {
            // A-Z
            builder.append((char) i);
        }
        List<String> dict = Arrays.asList(builder.toString().split(""));
        Collections.shuffle(dict);
        return String.join("", dict);
    }
}
