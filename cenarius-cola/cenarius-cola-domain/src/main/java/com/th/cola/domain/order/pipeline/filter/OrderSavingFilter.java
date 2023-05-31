package com.th.cola.domain.order.pipeline.filter;

import com.th.cenarius.commons.pattern.pipeline.filter.AbstractBizFilter;
import com.th.cola.domain.order.gateway.ISalesOrderPipelineGateway;
import com.th.cola.domain.order.pipeline.SalesOrderContext;
import com.th.cola.dto.event.SalesOrderEvents;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import lombok.RequiredArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/25
 */
@RequiredArgsConstructor
public class OrderSavingFilter extends AbstractBizFilter<SalesOrderContext> {

    private final ISalesOrderPipelineGateway pipelineGateway;

    @Resource
    private ApplicationEventPublisher salesOrderEvent;
    @Override
    protected void handle(SalesOrderContext context) {
        salesOrderEvent.publishEvent(new SalesOrderEvents.SalesOrderCreateEvent(context.getSalesOrder()));
    }
}
