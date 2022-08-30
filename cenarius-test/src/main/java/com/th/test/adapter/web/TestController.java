package com.th.test.adapter.web;

import com.th.test.domain.Order;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Aaron
 * @Date: 2022/3/10
 */
@RestController
public class TestController {

    @PostMapping(value = "testString", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String testString(@RequestBody String key) {
        return key;
    }

    @PostMapping(value = "testObject")
    @ResponseStatus(HttpStatus.OK)
    public Order testObject(@RequestBody Order key) {
        return key;
    }
}
