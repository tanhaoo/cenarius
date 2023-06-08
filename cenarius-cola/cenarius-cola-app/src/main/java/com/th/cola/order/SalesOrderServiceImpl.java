package com.th.cola.order;

//package by domain, not by duty


import com.th.cenarius.commons.pattern.pipeline.filter.selector.LocalListBasedFilterSelector;
import com.th.cola.BizEnum;
import com.th.cola.api.order.ISalesOrderService;
import com.th.cola.domain.order.pipeline.SalesOrderContext;
import com.th.cola.domain.order.pipeline.filter.BizValidationFilter;
import com.th.cola.domain.order.pipeline.filter.CouponApplyFilter;
import com.th.cola.domain.order.pipeline.filter.FreightCalculateFilter;
import com.th.cola.domain.order.pipeline.filter.LockStockFilter;
import com.th.cola.domain.order.pipeline.filter.OrderSavingFilter;
import com.th.cola.domain.order.pipeline.filter.PromotionCalculateFilter;
import com.th.cola.domain.order.service.ISalesOrderDomainService;
import com.th.cola.dto.SalesOrderAddCmd;
import com.th.cola.order.convertor.SalesOrderAppConvertor;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SalesOrderServiceImpl implements ISalesOrderService {

    @Resource
    private SalesOrderAppConvertor salesOrderAppConvertor;

    @Resource
    private ISalesOrderDomainService salesOrderDomainService;

    @Override
    public void submitOrder(SalesOrderAddCmd cmd) {
        SalesOrderContext context = new SalesOrderContext(BizEnum.BIZ_ORDER, getSalesOrderContext());
        context.setSalesOrder(salesOrderAppConvertor.toSalesOrder(cmd));

        salesOrderDomainService.orderCreate(context);

        cmd.getExecResult().setId(context.getSalesOrder().getSalesOrderId());
    }

    private static LocalListBasedFilterSelector getSalesOrderContext() {
        LocalListBasedFilterSelector selector = new LocalListBasedFilterSelector();
        selector.addFilter(BizValidationFilter.class.getSimpleName());
        selector.addFilter(CouponApplyFilter.class.getSimpleName());
        selector.addFilter(FreightCalculateFilter.class.getSimpleName());
        selector.addFilter(LockStockFilter.class.getSimpleName());
        selector.addFilter(OrderSavingFilter.class.getSimpleName());
        selector.addFilter(PromotionCalculateFilter.class.getSimpleName());
        return selector;
    }
}