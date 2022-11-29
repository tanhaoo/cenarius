package com.th.cenarius.web.advice;

import com.alibaba.fastjson.JSON;
import com.th.cenarius.web.ResultResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author: Aaron
 * @Date: 2022/3/10
 */

@RestControllerAdvice
@Slf4j
public class ResponseBodyHandlerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {

        // 如果已经被异常捕捉封装，直接返回
        if (body instanceof ResultResponse) {
            return body;
        }

        final ResultResponse.ResultResponseBuilder<?, ?> builder = ResultResponse.builder();
        final ResponseStatus annotation = Objects.requireNonNull(returnType.getMethod()).getAnnotation(ResponseStatus.class);
        if (annotation != null) {
            builder.status(annotation.value().value());
        }

        if (body != null) {
            builder.data(body);
        }

        log.error(returnType.toString());
        log.error(returnType.getMethod().toString());
        log.error(selectedContentType.toString());
        log.error(selectedConverterType.toString());
        if (body instanceof String) {
            return JSON.toJSONString(builder.build());
        }
        return builder.build();
    }
}
