package com.th.cenarius.codegen.processor.dto;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Aaron
 * @Date: 2022/8/31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenDto {

    String packageName();

    String sourcePath() default "src/main/java";

    boolean overrideSource() default false;
}
