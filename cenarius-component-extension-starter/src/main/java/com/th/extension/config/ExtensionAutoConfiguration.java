package com.th.extension.config;

import com.th.extension.ExtensionBootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Aaron
 * @Date: 2023/5/31
 */
@ComponentScan(
        basePackages = {"com.th.extension"}
)
@Configuration
public class ExtensionAutoConfiguration {


}
