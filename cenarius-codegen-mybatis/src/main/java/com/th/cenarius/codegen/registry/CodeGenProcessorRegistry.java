package com.th.cenarius.codegen.registry;

import com.google.common.collect.Maps;

import com.th.cenarius.codegen.spi.CodeGenProcessor;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * Load all of CodeGenProcessor by SPI
 * Identify the marker classes that need to be processed
 *
 * @Author: Aaron
 * @Date: 2022/8/30
 */
public class CodeGenProcessorRegistry {

    private static Map<String, ? extends CodeGenProcessor> PROCESSORS;

    private CodeGenProcessorRegistry() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get Supported Annotations
     *
     * @return Set of String
     */
    public static Set<String> getSupportedAnnotations() {

        return PROCESSORS.keySet();
    }

    /**
     * Get CodeGenProcessor by annotationClassName
     *
     * @param annotationClassName Class Name of annotation
     * @return {@link CodeGenProcessor} instance
     */
    public static CodeGenProcessor find(String annotationClassName) {
        return PROCESSORS.get(annotationClassName);
    }

    /**
     * Load all processors
     */
    public static void initProcessors() {
        Map<String, CodeGenProcessor> map = Maps.newLinkedHashMap();
        ServiceLoader<CodeGenProcessor> processors = ServiceLoader.load(CodeGenProcessor.class, CodeGenProcessor.class.getClassLoader());
        for (CodeGenProcessor next : processors) {
            Class<? extends Annotation> annotation = next.getAnnotation();
            map.put(annotation.getName(), next);
        }
        PROCESSORS = map;
    }
}
