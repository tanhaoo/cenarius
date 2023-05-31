package com.th.cola.domain.order.gateway;

import com.th.cola.domain.order.model.SalesOrder;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
public interface ISalesOrderGateway {

    String saveSalesOrder(SalesOrder salesOrder);

    boolean saveSalesOrderItems(SalesOrder salesOrder);

    boolean saveSalesOrderLog(SalesOrder salesOrder);

    boolean saveSalesOrderPromotionRecord(SalesOrder salesOrder);
}
