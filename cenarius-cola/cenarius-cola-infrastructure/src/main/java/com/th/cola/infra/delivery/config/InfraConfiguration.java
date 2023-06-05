package com.th.cola.infra.delivery.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2023/6/5
 */
@Configuration("deliveryAppConfiguration")
@ConfigurationProperties(prefix = "env")
@RefreshScope
@Data
public class InfraConfiguration {

    private String model = "Standard";
}
