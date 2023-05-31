package com.th.cola.domain;

import com.th.cola.domain.order.event.SalesOrderCreateEventHandler;
import com.th.cola.domain.order.service.ISalesOrderDomainService;
import com.th.cola.domain.order.service.SalesOrderDomainServiceImpl;
import com.th.cola.dto.SalesOrderAddCmd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
@ExtendWith(SpringExtension.class)
@Import({SalesOrderDomainServiceImpl.class, SalesOrderCreateEventHandler.class})
public class SalesOrderEntityTest {

    @Resource
    private ISalesOrderDomainService salesOrderDomainService;

    @Test
    public void testCreateOrder() {
//        salesOrderDomainService.orderCreate(new SalesOrderAddCmd("Order", new BigDecimal("32.99"), "Aaron"));
    }
}
