package com.th.test.app.service;

import com.th.cenarius.web.common.pipeline.PipelineExecutor;
import com.th.test.pattern.pipeline.coupon.batch.BatchCouponContext;
import com.th.test.pattern.pipeline.coupon.generate.CouponBuildContext;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/12/2
 */
@Service
@Slf4j
public class CouponService {

    @Resource(name = "couponExecutor")
    private PipelineExecutor couponExecutor;

    public List<String> getCouponCodes() {
        CouponBuildContext couponContext = CouponBuildContext.builder()
                .partCount(2).partLen(3)
                .prefix("PRE").suffix("SUF")
                .separator("-")
                .dictionary(baseDictString()).build();

        BatchCouponContext batchCouponContext = BatchCouponContext.builder().couponContext(couponContext).quantity(10).lastIndex(1.0).build();

        couponExecutor.acceptSync(batchCouponContext);

//        couponExecutor.acceptAsync(batchCouponContext, (context, res) -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            BatchCouponContext batch = (BatchCouponContext) context;
//
//            log.info("{} callback, data {}", batch.getSimpleName(), batch.getCouponCodes());
//        });

        log.info("{} arrive, data {}", batchCouponContext.getSimpleName(), batchCouponContext.getCouponCodes());
        return batchCouponContext.getCouponCodes();
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
