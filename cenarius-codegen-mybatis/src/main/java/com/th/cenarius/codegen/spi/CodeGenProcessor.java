package com.th.cenarius.codegen.spi;

import java.lang.annotation.Annotation;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * @Author: Aaron
 * @Date: 2022/8/30
 */
public interface CodeGenProcessor {

    /**
     * Analyze the annotations in the class
     *
     * @return {@link Class} instance
     */
    Class<? extends Annotation> getAnnotation();

    /**
     * Get root of generate package
     *
     * @param element {@link TypeElement} instance
     * @return package
     */
    String generatePackage(TypeElement element);

    /**
     * Generate code
     *
     * @param element          {@link TypeElement} instance
     * @param roundEnvironment {@link RoundEnvironment} instance
     * @throws Exception {@link Exception} instance
     */
    void generate(TypeElement element, RoundEnvironment roundEnvironment) throws Exception;
}
