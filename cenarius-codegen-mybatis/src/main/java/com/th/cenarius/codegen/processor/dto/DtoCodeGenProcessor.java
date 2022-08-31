package com.th.cenarius.codegen.processor.dto;

import com.google.auto.service.AutoService;

import com.squareup.javapoet.TypeSpec;
import com.th.cenarius.codegen.processor.BaseCodeGenProcessor;
import com.th.cenarius.codegen.spi.CodeGenProcessor;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2022/8/31
 */
@AutoService(CodeGenProcessor.class)
public class DtoCodeGenProcessor extends BaseCodeGenProcessor {

    public static final String SUFFIX = "DTO";

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        // Find all fields by Variable Element,excluding attributes with Ignore annotation
        Set<VariableElement> fields = findFields(typeElement,
                ve -> Objects.isNull(ve.getAnnotation(IgnoreDto.class)));

        String className = typeElement.getSimpleName() + SUFFIX;
        String sourceClassName = typeElement.getSimpleName() + SUFFIX;

        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Schema.class)
                .addAnnotation(Data.class)
                .addAnnotation(AllArgsConstructor.class)
                .addAnnotation(NoArgsConstructor.class);

        addSetterAndGetterMethod(classBuilder, fields);

        String packageName = generatePackage(typeElement);

//        genJavaFile(packageName, classBuilder);
        genJavaSourceFile(packageName, typeElement.getAnnotation(GenDto.class).sourcePath(), classBuilder);
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return GenDto.class;
    }

    @Override
    public String generatePackage(TypeElement element) {
        return element.getAnnotation(GenDto.class).packageName();
    }
}
