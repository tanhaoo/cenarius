package com.th.test.pattern.pipeline.coupon.generate;

import com.th.cenarius.web.common.pipeline.PipelineContext;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2022/11/30
 */
@Data
@Builder
public class CouponBuildContext extends PipelineContext {

    private int partLen;

    private int partCount;

    private String separator;

    @NotBlank
    private String prefix;

    @NotBlank
    private String suffix;

    private double curIndex;

    private String dictionary;

    @Builder.Default
    private StringBuilder curCodeBuilder = new StringBuilder();
}
