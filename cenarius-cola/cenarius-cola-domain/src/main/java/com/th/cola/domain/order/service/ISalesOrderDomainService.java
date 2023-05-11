package com.th.cola.domain.order.service;

import com.th.cola.dto.SalesOrderAddCmd;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
public interface ISalesOrderDomainService {

    boolean orderCreate(SalesOrderAddCmd cmd);

    boolean orderRevise();

    boolean orderComplete();

    boolean orderCancel(String orderId);
}
