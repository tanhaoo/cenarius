package com.th.cola.infra.order.gateway;

import com.th.cola.domain.delivery.gateway.IDeliveryGateway;
import com.th.cola.domain.order.gateway.ISalesOrderGateway;
import com.th.cola.domain.order.gateway.ISalesOrderPipelineGateway;
import com.th.cola.domain.product.gateway.IProductGateway;
import com.th.cola.domain.promotion.gateway.IPromotionGateway;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/25
 */
@RequiredArgsConstructor
@Component
public class SalesOrderPipelineGatewayImpl implements ISalesOrderPipelineGateway {

    private final ISalesOrderGateway salesOrderGateway;

    private final IProductGateway productGateway;

    private final IPromotionGateway promotionGateway;

    private final IDeliveryGateway deliveryGateway;

    @Override
    public ISalesOrderGateway getSalesOrderGateway() {
        return salesOrderGateway;
    }

    @Override
    public IProductGateway getProductGateway() {
        return productGateway;
    }

    @Override
    public IPromotionGateway getPromotionGateway() {
        return promotionGateway;
    }

    @Override
    public IDeliveryGateway getDeliveryGateway() {
        return deliveryGateway;
    }
}
