package com.th.cola.order.convertor;

import com.th.cola.domain.order.model.SalesOrder;
import com.th.cola.domain.order.model.SalesOrderItem;
import com.th.cola.domain.product.model.Product;
import com.th.cola.dto.SalesOrderAddCmd;

import org.mapstruct.Mapper;

/**
 * @Author: Aaron
 * @Date: 2023/5/26
 */
@Mapper(componentModel = "spring")
public interface SalesOrderAppConvertor {

    SalesOrder toSalesOrder(SalesOrderAddCmd cmd);

    SalesOrderItem toSalesOrderItem(SalesOrderAddCmd.SalesOrderItem item);

    Product toSalesOrderItem(SalesOrderAddCmd.Product product);
}
