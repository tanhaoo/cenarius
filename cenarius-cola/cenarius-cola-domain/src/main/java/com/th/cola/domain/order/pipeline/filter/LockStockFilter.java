package com.th.cola.domain.order.pipeline.filter;

import com.th.cenarius.commons.pattern.pipeline.filter.AbstractBizFilter;
import com.th.cola.domain.order.gateway.ISalesOrderPipelineGateway;
import com.th.cola.domain.order.model.SalesOrderItem;
import com.th.cola.domain.order.pipeline.SalesOrderContext;

import java.lang.reflect.ParameterizedType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
@RequiredArgsConstructor
@Slf4j
public class LockStockFilter extends AbstractBizFilter<SalesOrderContext> {

    private final ISalesOrderPipelineGateway salesOrderPipelineGateway;

    @Override
    protected void handle(SalesOrderContext context) {
        context
                .getSalesOrder()
                .getItems()
                .stream()
                .map(SalesOrderItem::getProduct)
                .forEach(salesOrderPipelineGateway.getProductGateway()::lockStock);
    }
}
