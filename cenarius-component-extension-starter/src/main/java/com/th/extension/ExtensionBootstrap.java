package com.th.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.annotation.Resource;

import lombok.NonNull;

/**
 * @Author: Aaron
 * @Date: 2023/5/31
 */
@Component
public class ExtensionBootstrap implements ApplicationContextAware, SmartInitializingSingleton {
    private ApplicationContext applicationContext;

    @Resource
    private ExtensionRepository extensionRepository;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> extBeans = applicationContext.getBeansWithAnnotation(Extension.class);
        extBeans.values().forEach(extensionRepository::register);
    }

}
