package com.th.cenarius.commons.pattern.pipeline.filter;

import com.th.cenarius.commons.pattern.pipeline.OrderContext;
import com.th.cenarius.commons.pattern.pipeline.filter.AbstractBizFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
@Slf4j
public class CalculatePromoFilter extends AbstractBizFilter<OrderContext> {

    @Override
    protected void handle(OrderContext context) {
        log.info("开始通过Promotion Service计算订单金额, Order id: {}", context.getOrderModel().getOrderId());
    }
}
