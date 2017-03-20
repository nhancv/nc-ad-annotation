package com.nhancv.compiler;

import com.google.auto.service.AutoService;
import com.nhancv.nannotation.Data;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class NProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Data.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Data.class)) {
            process((TypeElement) annotatedElement);
        }
        return true;
    }

    private void process(TypeElement annotatedType) {
        String packageName = getPackageNameOf(annotatedType);

        MethodSpec hello = MethodSpec.methodBuilder("hello")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class, "name")
                .addStatement("$T.out.println($L)", System.class, "name")
                .build();

        TypeSpec generatedClass = TypeSpec.classBuilder("_" + annotatedType.getSimpleName())
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(hello)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, generatedClass).build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPackageNameOf(Element element) {
        return processingEnv.getElementUtils().getPackageOf(element).toString();
    }

}
