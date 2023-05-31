package com.th.cola.infra.product.gateway;

import com.th.cola.domain.product.gateway.IProductGateway;
import com.th.cola.domain.product.model.Product;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/25
 */
@Slf4j
@Component
public class ProductGatewayImpl implements IProductGateway {
    @Override
    public boolean lockStock(Product product) {
        log.info("Start lock stock, SPU Code: {}, SKU Code: {}", product.getSpuCode(), product.getSkuCode());
        return true;
    }
}
