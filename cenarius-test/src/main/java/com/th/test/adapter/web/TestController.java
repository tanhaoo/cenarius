package com.th.test.adapter.web;

import com.th.cenarius.web.annotation.ExceptionHandleAnno;
import com.th.cenarius.web.annotation.InvokeRecordAnno;
import com.th.test.app.service.CouponService;
import com.th.test.app.service.OrderService;
import com.th.test.domain.Order;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2022/3/10
 */
@RestController
public class TestController {

    @Resource
    private OrderService orderService;

    @Resource
    private CouponService couponService;

    @PostMapping(value = "testString", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String testString(@RequestBody String key) {
        return key;
    }

    @PostMapping(value = "testObject")
    @ResponseStatus(HttpStatus.OK)
    @InvokeRecordAnno("Proxy Model Test")
    @ExceptionHandleAnno
    public Order testObject(@RequestBody @Validated Order key) {
        if (key.getId() == 1) {
            throw new RuntimeException("Unexpected operation type");
        }
        return key;
    }

    @PostMapping(value = "order")
    @ResponseStatus(HttpStatus.OK)
    public void order(@RequestBody Order key) {
        orderService.submit(key);
    }


    @GetMapping(value = "coupon")
    @ResponseStatus(HttpStatus.OK)
    public List<String> couponCodes() {

        return couponService.getCouponCodes();
    }


}
