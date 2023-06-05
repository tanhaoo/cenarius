package com.th.cola.web.delivery;

import com.th.cola.api.devliery.IDeliveryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/6/5
 */
@RestController
@RequestMapping("delivery")
public class DeliveryController {

    @Resource
    private IDeliveryService deliveryService;

    @GetMapping(path = "/freight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal calculateFreight() {
        return deliveryService.calcuateFreight();
    }
}
