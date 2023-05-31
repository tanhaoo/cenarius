package com.th.cola.web.order;

import com.th.cola.api.order.ISalesOrderService;
import com.th.cola.dto.SalesOrderAddCmd;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/5/30
 */
@RestController
@RequestMapping("order")
public class SalesOrderController {

    @Resource
    private ISalesOrderService salesOrderService;

    @PostMapping(path = "/salesOrders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SalesOrderAddCmd.SalesOrderSubmitCmdResult submitOrder(@RequestBody SalesOrderAddCmd cmd) {
        salesOrderService.submitOrder(cmd);
        return cmd.getExecResult();
    }
}
