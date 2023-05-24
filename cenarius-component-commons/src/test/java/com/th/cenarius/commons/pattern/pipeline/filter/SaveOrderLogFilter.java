package com.th.cenarius.commons.pattern.pipeline.filter;

import com.th.cenarius.commons.pattern.pipeline.OrderContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
@Slf4j
public class SaveOrderLogFilter extends AbstractBizFilter<OrderContext> {

    @Override
    protected void handle(OrderContext context) {
        log.info("开始订单日志信息保存, Order id: {}", context.getOrderModel().getOrderId());
    }
}
