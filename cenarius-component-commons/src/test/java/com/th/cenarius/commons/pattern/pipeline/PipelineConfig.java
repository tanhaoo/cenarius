package com.th.cenarius.commons.pattern.pipeline;

import com.th.cenarius.commons.pattern.pipeline.filter.AbstractBizFilter;
import com.th.cenarius.commons.pattern.pipeline.filter.CalculatePromoFilter;
import com.th.cenarius.commons.pattern.pipeline.filter.CheckBizFilter;
import com.th.cenarius.commons.pattern.pipeline.filter.SaveOrderFilter;
import com.th.cenarius.commons.pattern.pipeline.filter.SaveOrderLogFilter;

import org.springframework.context.annotation.Bean;

/**
 * @Author: Aaron
 * @Date: 2023/5/19
 */
public class PipelineConfig {

    @Bean
    public FilterChainPipeline<AbstractBizFilter<OrderContext>> orderPipeline() {
        FilterChainPipeline<AbstractBizFilter<OrderContext>> orderPipeline = new FilterChainPipeline<>("order");

        orderPipeline
                .addFilter("校验参数", checkBizFilter())
                .addFilter("计算优惠", promoFilter())
                .addFilter("下单入库", orderFilter())
                .addFilter("下单记录入库", orderLogFilter());

        return orderPipeline;
    }

    public CheckBizFilter checkBizFilter() {
        return new CheckBizFilter();
    }

    public CalculatePromoFilter promoFilter() {
        return new CalculatePromoFilter();
    }

    public SaveOrderFilter orderFilter() {
        return new SaveOrderFilter();
    }

    public SaveOrderLogFilter orderLogFilter() {
        return new SaveOrderLogFilter();
    }
}
