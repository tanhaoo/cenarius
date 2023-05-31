package com.th.cola.domain.order.event;

import com.th.cola.domain.order.gateway.ISalesOrderGateway;
import com.th.cola.domain.order.model.SalesOrder;
import com.th.cola.dto.event.SalesOrderEvents;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
@Slf4j
@Component
public class SalesOrderCreateEventHandler {

    @Resource
    private ISalesOrderGateway salesOrderGateway;

    @EventListener
    public void handleForDB(SalesOrderEvents.SalesOrderCreateEvent event) {
        SalesOrder salesOrder = (SalesOrder) event.getSource();
        log.info("Handle SalesOrderCreateEvent for DB, {}", salesOrder.getSalesOrderId());

        String orderId = salesOrderGateway.saveSalesOrder(salesOrder);
    }

    @EventListener
    public void handleForES(SalesOrderEvents.SalesOrderCreateEvent event) {
        SalesOrder salesOrder = (SalesOrder) event.getSource();
        log.info("Handle SalesOrderCreateEvent for ES, {}", salesOrder.getSalesOrderId());

    }

    @EventListener
    public void handleForSalesOrderItem(SalesOrderEvents.SalesOrderCreateEvent event) {
        SalesOrder salesOrder = (SalesOrder) event.getSource();
        log.info("Handle SalesOrderCreateEvent for Item, {}", salesOrder.getSalesOrderId());

        salesOrderGateway.saveSalesOrderItems(salesOrder);
    }
}
