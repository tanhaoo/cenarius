package com.th.cola.api.order;

import com.th.cola.dto.SalesOrderAddCmd;

/**
 * @Author: Aaron
 * @Date: 2023/5/25
 */
public interface ISalesOrderService {

    /**
     * Submit order
     *
     * @param cmd {@link SalesOrderAddCmd instance}
     */
    void submitOrder(SalesOrderAddCmd cmd);
}
