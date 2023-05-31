package com.th.cola.domain.order.pipeline.config;

import com.th.cenarius.commons.pattern.pipeline.FilterChainPipeline;
import com.th.cola.domain.order.gateway.ISalesOrderPipelineGateway;
import com.th.cola.domain.order.pipeline.filter.BizValidationFilter;
import com.th.cola.domain.order.pipeline.filter.CouponApplyFilter;
import com.th.cola.domain.order.pipeline.filter.FreightCalculateFilter;
import com.th.cola.domain.order.pipeline.filter.LockStockFilter;
import com.th.cola.domain.order.pipeline.filter.OrderSavingFilter;
import com.th.cola.domain.order.pipeline.filter.PromotionCalculateFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
@Configuration
public class PipelineConfig {

    @Resource
    private ISalesOrderPipelineGateway salesOrderPipelineGateway;

    @Bean
    public FilterChainPipeline orderPipeline() {
        FilterChainPipeline pipeline = new FilterChainPipeline<>("order create");
        pipeline
                .addFilter("锁定库存", lockStockFilter())
                .addFilter("业务检查", bizValidationFilter())
                .addFilter("运费计算", freightCalculateFilter())
                .addFilter("优惠计算", promotionCalculateFilter())
                .addFilter("优惠券使用", couponExchangeFilter())
                .addFilter("订单保存", orderSavingFilter());

        return pipeline;
    }

    private LockStockFilter lockStockFilter() {
        return new LockStockFilter(salesOrderPipelineGateway);
    }

    private BizValidationFilter bizValidationFilter() {
        return new BizValidationFilter(salesOrderPipelineGateway);
    }

    private FreightCalculateFilter freightCalculateFilter() {
        return new FreightCalculateFilter(salesOrderPipelineGateway);
    }

    private PromotionCalculateFilter promotionCalculateFilter() {
        return new PromotionCalculateFilter(salesOrderPipelineGateway);
    }

    @Bean
    public OrderSavingFilter orderSavingFilter() {
        return new OrderSavingFilter(salesOrderPipelineGateway);
    }

    private CouponApplyFilter couponExchangeFilter() {
        return new CouponApplyFilter(salesOrderPipelineGateway);
    }
}
