package com.th.cola.domain.order.service;

import com.th.cola.domain.order.pipeline.SalesOrderContext;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
public interface ISalesOrderDomainService {

    boolean orderCreate(SalesOrderContext context);

    boolean orderRevise();

    boolean orderComplete();

    boolean orderCancel(String orderId);
}
