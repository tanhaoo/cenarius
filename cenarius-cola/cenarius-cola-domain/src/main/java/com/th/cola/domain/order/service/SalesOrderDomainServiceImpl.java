package com.th.cola.domain.order.service;

import com.th.cola.domain.order.SalesOrder;
import com.th.cola.dto.SalesOrderAddCmd;
import com.th.cola.dto.event.SalesOrderEvents;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
@Component
public class SalesOrderDomainServiceImpl implements ISalesOrderDomainService {

    @Resource
    private ApplicationEventPublisher salesOrderEvent;

    @Override
    public boolean orderCreate(SalesOrderAddCmd cmd) {
        SalesOrder salesOrder = new SalesOrder("1", cmd.getDescription(), cmd.getTotalAmount(), cmd.getOperateUser());
        salesOrderEvent.publishEvent(new SalesOrderEvents.SalesOrderCreateEvent(salesOrder));
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
