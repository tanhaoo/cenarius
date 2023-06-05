package com.th.cola.infra.delivery.gateway;

import com.th.cola.domain.delivery.gateway.IDeliveryGateway;
import com.th.cola.infra.delivery.config.InfraConfiguration;
import com.th.cola.infra.delivery.extension.DeliveryServiceExtPt;
import com.th.extension.ExtensionExecutor;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/5/25
 */
@Component
@Slf4j
public class DeliveryGatewayImpl implements IDeliveryGateway {

    @Resource
    private ExtensionExecutor extensionExecutor;

    @Resource
    private InfraConfiguration configuration;

    @Override
    public BigDecimal calculateFreight() {
        return new BigDecimal("5.5");
    }

    @Override
    public BigDecimal calculateFreightV2() {
        log.info(configuration.getModel());
        BigDecimal freight = extensionExecutor.execute(DeliveryServiceExtPt.class, configuration.getModel(), DeliveryServiceExtPt::calculateFreight);
        return freight;
    }
}
