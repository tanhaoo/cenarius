package com.th.cola.infra.order.gateway;

import com.th.cola.domain.order.gateway.ISalesOrderGateway;
import com.th.cola.domain.order.model.SalesOrder;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
@Component
@Slf4j
public class SalesOrderGatewayImpl implements ISalesOrderGateway {
    @Override
    public String saveSalesOrder(SalesOrder salesOrder) {
        log.info("Saving sales order, order id: {}", salesOrder.getSalesOrderId());
        return salesOrder.getSalesOrderId();
    }

    @Override
    public boolean saveSalesOrderItems(SalesOrder salesOrder) {
        salesOrder.getItems().forEach(item ->
                log.info("Saving sales order item, order id: {}, item id: {}", salesOrder.getSalesOrderId(), item.getItemId())
        );
        return false;
    }

    @Override
    public boolean saveSalesOrderLog(SalesOrder salesOrder) {
        return false;
    }

    @Override
    public boolean saveSalesOrderPromotionRecord(SalesOrder salesOrder) {
        return false;
    }
}
