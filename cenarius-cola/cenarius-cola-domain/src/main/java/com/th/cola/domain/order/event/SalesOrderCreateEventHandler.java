package com.th.cola.domain.order.event;

import com.th.cola.domain.order.SalesOrder;
import com.th.cola.dto.SalesOrderAddCmd;
import com.th.cola.dto.event.SalesOrderEvents;

import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
@Slf4j
public class SalesOrderCreateEventHandler {

    @EventListener
    public void handleForDB(SalesOrderEvents.SalesOrderCreateEvent event) {
        SalesOrder salesOrder = (SalesOrder) event.getSource();
        log.info("Handle SalesOrderCreateEvent for DB, {}", salesOrder);
    }

    @EventListener
    public void handleForES(SalesOrderEvents.SalesOrderCreateEvent event) {
        SalesOrder salesOrder = (SalesOrder) event.getSource();
        log.info("Handle SalesOrderCreateEvent for ES, {}", salesOrder);
    }

    @EventListener
    public void handleForSalesOrderItem(SalesOrderEvents.SalesOrderCreateEvent event) {
        SalesOrder salesOrder = (SalesOrder) event.getSource();
        log.info("Handle SalesOrderCreateEvent for Item, {}", salesOrder);
    }
}
