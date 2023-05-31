package com.th.cola.domain.order.gateway;

import com.th.cola.domain.delivery.gateway.IDeliveryGateway;
import com.th.cola.domain.product.gateway.IProductGateway;
import com.th.cola.domain.promotion.gateway.IPromotionGateway;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
public interface ISalesOrderPipelineGateway {

    ISalesOrderGateway getSalesOrderGateway();

    IProductGateway getProductGateway();

    IPromotionGateway getPromotionGateway();

    IDeliveryGateway getDeliveryGateway();
}
