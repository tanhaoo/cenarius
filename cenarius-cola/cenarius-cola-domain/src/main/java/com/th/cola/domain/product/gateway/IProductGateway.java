package com.th.cola.domain.product.gateway;

import com.th.cola.domain.product.model.Product;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
public interface IProductGateway {

    boolean lockStock(Product product);
}
