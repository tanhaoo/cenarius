package com.th.cola.domain.order.pipeline.filter;

import com.th.cenarius.commons.pattern.pipeline.filter.AbstractBizFilter;
import com.th.cola.domain.order.gateway.ISalesOrderPipelineGateway;
import com.th.cola.domain.order.pipeline.SalesOrderContext;

import lombok.RequiredArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/25
 */
@RequiredArgsConstructor
public class PromotionCalculateFilter extends AbstractBizFilter<SalesOrderContext> {
    private final ISalesOrderPipelineGateway pipelineGateway;

    @Override
    protected void handle(SalesOrderContext context) {

    }
}
