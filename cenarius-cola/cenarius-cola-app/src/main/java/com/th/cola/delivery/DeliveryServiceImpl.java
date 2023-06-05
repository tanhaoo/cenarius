package com.th.cola.delivery;

import com.th.cola.api.devliery.IDeliveryService;
import com.th.cola.domain.delivery.gateway.IDeliveryGateway;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/6/5
 */
@Component
@Slf4j
public class DeliveryServiceImpl implements IDeliveryService {
    @Resource
    private IDeliveryGateway deliveryGateway;

    @Override
    public BigDecimal calcuateFreight() {
        return deliveryGateway.calculateFreightV2();
    }
}
