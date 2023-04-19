package com.th.test.config.redisson;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author xiaofei.x.xu@henkel.com
 */
@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    @Bean(destroyMethod = "shutdown")
    @RefreshScope
    public RedissonClient redissonClient(RedissonProperties properties) {

        Boolean isCluster = properties.getCluster();

        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);


        List<String> addresses = getAddresses(properties);

        if (isCluster) {

            final ClusterServersConfig clusterConfig = config.useClusterServers();
            clusterConfig.setNodeAddresses(addresses);
            clusterConfig.setPassword(properties.getPassword());

        } else {

            final SingleServerConfig singleConfig = config.useSingleServer();
            singleConfig.setAddress(addresses.get(0));
            singleConfig.setPassword(properties.getPassword());

        }

        // return client
        return Redisson.create(config);
    }

    private List<String> getAddresses(RedissonProperties properties) {
        return List.of(properties.getServer().split(";"));
    }
}
