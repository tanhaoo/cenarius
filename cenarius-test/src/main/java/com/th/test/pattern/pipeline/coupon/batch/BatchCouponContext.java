package com.th.test.pattern.pipeline.coupon.batch;

import com.th.cenarius.web.common.pipeline.PipelineContext;
import com.th.test.pattern.pipeline.coupon.generate.CouponBuildContext;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2022/12/1
 */
@Data
@Builder
public class BatchCouponContext extends PipelineContext {

    private Double lastIndex;

    private Integer quantity;

    private CouponBuildContext couponContext;

    @Builder.Default
    private List<String> couponCodes = new ArrayList<>();
}
