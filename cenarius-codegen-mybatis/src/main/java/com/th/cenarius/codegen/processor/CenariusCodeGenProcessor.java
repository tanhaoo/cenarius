package com.th.cenarius.codegen.processor;

import com.google.auto.service.AutoService;

import com.th.cenarius.codegen.context.ProcessingEnvironmentHolder;
import com.th.cenarius.codegen.registry.CodeGenProcessorRegistry;
import com.th.cenarius.codegen.spi.CodeGenProcessor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

/**
 * @Author: Aaron
 * @Date: 2022/8/30
 */
@AutoService(Processor.class)
public class CenariusCodeGenProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        annotations.forEach(annotation -> {
            Set<? extends Element> typeElements = roundEnv.getElementsAnnotatedWith(annotation);
            Set<TypeElement> types = ElementFilter.typesIn(typeElements);
            for (TypeElement element : types) {
                CodeGenProcessor codeGenProcessor = CodeGenProcessorRegistry.find(annotation.getQualifiedName().toString());
                try {
                    codeGenProcessor.generate(element, roundEnv);
                } catch (Exception e) {
                    ProcessingEnvironmentHolder.getEnvironment().getMessager().printMessage(Diagnostic.Kind.ERROR, "Code generating abnormalities:" + e.getMessage());
                }
            }
        });
        return false;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        ProcessingEnvironmentHolder.setEnvironment(processingEnv);
        CodeGenProcessorRegistry.initProcessors();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return CodeGenProcessorRegistry.getSupportedAnnotations();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }



}
