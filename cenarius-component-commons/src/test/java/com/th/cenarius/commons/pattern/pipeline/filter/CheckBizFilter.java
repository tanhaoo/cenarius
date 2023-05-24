package com.th.cenarius.commons.pattern.pipeline.filter;

import com.th.cenarius.commons.pattern.pipeline.OrderContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
@Slf4j
public class CheckBizFilter extends AbstractBizFilter<OrderContext> {

    @Override
    protected void handle(OrderContext context) {
        log.info("开始校验业务参数, Order id: {}", context.getOrderModel().getOrderId());
    }
}
