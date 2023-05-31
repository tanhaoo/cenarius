package com.th.cola.infra.delivery.gateway;

import com.th.cola.domain.delivery.gateway.IDeliveryGateway;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/5/25
 */
@Component
public class DeliveryGatewayImpl implements IDeliveryGateway {
    @Override
    public BigDecimal calculateFreight() {
        return new BigDecimal("9.9");
    }
}
