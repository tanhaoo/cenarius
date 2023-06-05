package com.th.cola.domain.order.service;

import com.th.cenarius.commons.pattern.pipeline.FilterChainPipeline;
import com.th.cola.domain.order.pipeline.SalesOrderContext;

import org.springframework.stereotype.Component;

import java.util.UUID;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
@Component
@Slf4j
public class SalesOrderDomainServiceImpl implements ISalesOrderDomainService {

    @Resource(name = "orderPipeline")
    private FilterChainPipeline orderPipeline;

    @Override
    public boolean orderCreate(SalesOrderContext context) {
        context.getSalesOrder().setSalesOrderId(UUID.randomUUID().toString());
        orderPipeline.getFilterChain().handle(context);

        log.info("Sales Order: {}", context.getSalesOrder());

        return true;
    }

    @Override
    public boolean orderRevise() {
        return false;
    }

    @Override
    public boolean orderComplete() {
        return false;
    }

    @Override
    public boolean orderCancel(String orderId) {
        return false;
    }
}
