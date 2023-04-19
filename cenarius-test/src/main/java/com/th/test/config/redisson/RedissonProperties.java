package com.th.test.config.redisson;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2023/4/10
 */
@Data
@ConfigurationProperties(prefix = RedissonProperties.PREFIX)
@RefreshScope
public class RedissonProperties {

    /**
     * prefix
     */
    public static final String PREFIX = "redisson";

    /**
     * Whether it is cluster mode
     */
    private Boolean cluster;

    /**
     * list of redis servers
     */
    private String server;

    /**
     * password of redis
     */
    private String password;
}
