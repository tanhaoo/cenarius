package com.th.test.adapter.web;

import com.th.test.app.service.OrderService;
import com.th.test.domain.Order;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author: Aaron
 * @Date: 2022/3/10
 */
@RestController
public class TestController {

    @Resource
    private OrderService orderService;

    @PostMapping(value = "testString", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String testString(@RequestBody String key) {
        return key;
    }

    @PostMapping(value = "testObject")
    @ResponseStatus(HttpStatus.OK)
    public Order testObject(@RequestBody @Validated Order key) {
        return key;
    }

    @PostMapping(value = "order")
    @ResponseStatus(HttpStatus.OK)
    public void order(@RequestBody Order key) {
        orderService.submit(key);
    }

}
