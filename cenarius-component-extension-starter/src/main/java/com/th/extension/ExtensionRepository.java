package com.th.extension;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lombok.Getter;

/**
 * @Author: Aaron
 * @Date: 2023/6/1
 */
@Component
public class ExtensionRepository {

    @Getter
    private Map<ExtensionCoordinate, Object> extMaps = new HashMap<>();

    public final static String EXTENSION_EXT_PT_NAMING = "ExtPt";

    public void register(Object extBean) {
        Class<?> extClazz = extBean.getClass();
        if (AopUtils.isAopProxy(extClazz)) {
            extClazz = ClassUtils.getUserClass(extClazz);
        }

        AopProxyUtils.getSingletonTarget(extBean);

        Extension extAnno = AnnotationUtils.findAnnotation(extClazz, Extension.class);

        assert extAnno != null;
        ExtensionCoordinate coordinate = new ExtensionCoordinate(calculateExtensionPoint(extClazz), extAnno.bizId());
        Object pre = extMaps.put(coordinate, extBean);

        if (Objects.nonNull(pre)) {
            throw new RuntimeException(coordinate + " has exist!");
        }
    }

    private String calculateExtensionPoint(Class<?> extClazz) {
        Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(extClazz);

        for (Class<?> clz : interfaces) {
            if (clz.getSimpleName().contains(EXTENSION_EXT_PT_NAMING)) {
                return clz.getName();
            }
        }

        String errMessage = "Your name of ExtensionPoint for " + extClazz +
                " is not valid, must be end of " + EXTENSION_EXT_PT_NAMING;

        throw new RuntimeException(errMessage);
    }
}
