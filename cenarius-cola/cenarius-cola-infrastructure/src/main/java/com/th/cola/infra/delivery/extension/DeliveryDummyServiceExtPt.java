package com.th.cola.infra.delivery.extension;

import com.th.extension.Extension;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/6/5
 */
@Component
@Extension(bizId = "Dummy")
public class DeliveryDummyServiceExtPt implements DeliveryServiceExtPt {

    @Override
    public BigDecimal calculateFreight() {
        return new BigDecimal("0.99");
    }
}
