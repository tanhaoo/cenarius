package com.th.cenarius.codegen.processor;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.th.cenarius.codegen.annotation.FieldDesc;
import com.th.cenarius.codegen.context.ProcessingEnvironmentHolder;
import com.th.cenarius.codegen.spi.CodeGenProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author: Aaron
 * @Date: 2022/8/30
 */
public abstract class BaseCodeGenProcessor implements CodeGenProcessor {

    public static final String PREFIX = "Base";

    @Override
    public void generate(TypeElement element, RoundEnvironment roundEnvironment) {
        generateClass(element, roundEnvironment);
    }

    /**
     * Generate Class
     *
     * @param typeElement      {@link TypeElement} instance
     * @param roundEnvironment {@link RoundEnvironment} instance
     */
    protected abstract void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment);

    /**
     * Filter attribute
     * <p>
     * Element是一个接口，表示一个程序元素，它可以是包、类、方法或者一个变量。Element已知的子接口有:
     * <p>PackageElement 表示一个包程序元素。提供对有关包及其成员的信息的访问。</p>
     * <p>ExecutableElement 表示某个类或接口的方法、构造方法或初始化程序（静态或实例），包括注释类型元素。</p>
     * <p>TypeElement 表示一个类或接口程序元素。提供对有关类型及其成员的信息的访问。注意，枚举类型是一种类，而注解类型是一种接口。</p>
     * <p>VariableElement 表示一个字段、enum 常量、方法或构造方法参数、局部变量或异常参数。</p>
     *
     * @param typeElement {@link TypeElement} instance
     * @param predicate   {@link Predicate} instance
     * @return {@link Set} Set of Variable Element
     */
    public Set<VariableElement> findFields(TypeElement typeElement,
                                           Predicate<VariableElement> predicate) {
        List<? extends Element> fieldTypes = typeElement.getEnclosedElements();
        Set<VariableElement> variableElements = new LinkedHashSet<>();
        for (VariableElement e : ElementFilter.fieldsIn(fieldTypes)) {
            if (predicate.test(e)) {
                variableElements.add(e);
            }
        }
        return variableElements;
    }

    public void addSetterAndGetterMethod(TypeSpec.Builder classBuilder,
                                         Set<VariableElement> variableElements) {
        for (VariableElement ve : variableElements) {
            TypeName typeName = TypeName.get(ve.asType());

            // add field description by Swagger 3
            FieldSpec.Builder fieldSpec = FieldSpec
                    .builder(typeName, ve.getSimpleName().toString(), Modifier.PRIVATE)
                    .addAnnotation(AnnotationSpec.builder(Schema.class)
                            .addMember("title", "$S", getFieldDesc(ve))
                            .build());
            classBuilder.addField(fieldSpec.build());

//            String fieldName = getFieldDefaultName(ve);
//            MethodSpec.Builder getMethod = MethodSpec.methodBuilder("get" + fieldName)
//                    .returns(typeName)
//                    .addModifiers(Modifier.PUBLIC)
//                    .addStatement("return $L", ve.getSimpleName().toString());
//            MethodSpec.Builder setMethod = MethodSpec.methodBuilder("set" + fieldName)
//                    .returns(void.class)
//                    .addModifiers(Modifier.PUBLIC)
//                    .addParameter(typeName, ve.getSimpleName().toString())
//                    .addStatement("this.$L = $L",
//                            ve.getSimpleName().toString(),
//                            ve.getSimpleName().toString());
//
//            classBuilder.addMethod(getMethod.build());
//            classBuilder.addMethod(setMethod.build());
        }
    }

    /**
     * Get field description by FieldDesc Annotation
     *
     * @param ve {@link VariableElement} instances
     * @return field description
     */
    protected String getFieldDesc(VariableElement ve) {
        return Optional.ofNullable(ve.getAnnotation(FieldDesc.class))
                .map(FieldDesc::name).orElse(ve.getSimpleName().toString());
    }

    /**
     * Get field name
     *
     * @param ve {@link VariableElement} instances
     * @return field name
     */
    protected String getFieldDefaultName(VariableElement ve) {
        return ve.getSimpleName().toString().substring(0, 1).toUpperCase() +
                ve.getSimpleName().toString().substring(1);
    }


    /**
     * Generate java source file
     *
     * @param packageName     name of package
     * @param pathStr         path
     * @param typeSpecBuilder {@link TypeSpec} instance
     */
    public void genJavaSourceFile(String packageName, String pathStr,
                                  TypeSpec.Builder typeSpecBuilder) {
        TypeSpec typeSpec = typeSpecBuilder.build();
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec)
                .addFileComment("---Auto Generated by Cenarius ---")
                .build();

        System.out.println("Java File: " + javaFile);

        String packagePath =
                packageName.replace(".", File.separator) + File.separator + typeSpec.name + ".java";
        try {
            Path path = Paths.get(pathStr);
            File file = new File(path.toFile().getAbsolutePath());
            if (!file.exists()) {
                return;
            }
            String sourceFileName = path.toFile().getAbsolutePath() + File.separator + packagePath;
            File sourceFile = new File(sourceFileName);
            if (!sourceFile.exists()) {
                javaFile.writeTo(file);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Generate runtime file
     *
     * @param packageName     name of package
     * @param typeSpecBuilder {@link TypeSpec} instance
     */
    protected void genJavaFile(String packageName, TypeSpec.Builder typeSpecBuilder) {
        JavaFile javaFile = JavaFile.builder(packageName, typeSpecBuilder.build())
                .addFileComment("---Auto Generated by Cenarius ---")
                .build();
        try {
            javaFile.writeTo(ProcessingEnvironmentHolder.getEnvironment().getFiler());
        } catch (IOException e) {
            ProcessingEnvironmentHolder.getEnvironment().getMessager()
                    .printMessage(Diagnostic.Kind.ERROR, e.getMessage());
        }
    }
}
