package com.th.cenarius.web.advice;

import com.th.cenarius.web.ResultResponse;
import com.th.cenarius.exception.BizException;
import com.th.cenarius.exception.ErrorDetail;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

/**
 * @Author: Aaron
 * @Date: 2022/3/11
 */
@ControllerAdvice
@Slf4j
public class DefaultResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 处理不支持的Media类型的异常
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {

        // 构建错误内容
        final ResultResponse msg = ResultResponse.builder()
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .error(UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
                .build();
        // log
        log.info("Error occurred. code:{}, details:{}", msg.getStatus(), msg.getData());

        // 返回响应 415
        return ResponseEntity
                .status(UNSUPPORTED_MEDIA_TYPE)
                .body(msg);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<HashMap<String, String>> information = ex.getBindingResult().getAllErrors().stream().map(item -> {
            HashMap<String, String> map = new HashMap<>();
            FieldError val = (FieldError) item;
            map.put("field", val.getField());
            map.put("description", val.getDefaultMessage());
            return map;
        }).collect(Collectors.toList());

        final ResultResponse msg = ResultResponse.builder()
                .status(BAD_REQUEST.value())
                .error(information)
                .build();

        // 返回响应
        return ResponseEntity
                .badRequest()
                .body(msg);
    }


    /**
     * 处理预想以外的异常
     *
     * @param ex {@link Exception}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptionInternal(Exception ex) {

        // log
        log.error("Error occurred.", ex);

        final ResultResponse msg = ResultResponse.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .error(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        return ResponseEntity
                .internalServerError()
                .body(msg);
    }

    /**
     * 处理{@link ConstraintViolationException}异常。
     * <p>
     * 主要用于HTTP请求消息的路径参数，及Query参数的验证处理。
     *
     * @param ex {@link ConstraintViolationException}异常
     * @return {@link ResponseEntity}实例
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex) {

        final List<Object> details = ex.getConstraintViolations().stream()
                .map(cv -> {
                    final Annotation annotation = cv.getConstraintDescriptor().getAnnotation();

                    // 获取参数的名称
                    String arg = cv.getPropertyPath().toString();
                    for (Path.Node node : cv.getPropertyPath()) {
                        arg = node.getName();
                    }

                    // 构造错误明细
                    return ErrorDetail.builder()
                            .target(arg)
                            .type(annotation != null ? annotation.toString() : null)
                            .description(cv.getMessage())
                            .build();
                })
                .collect(Collectors.toList());

        final ResultResponse msg = ResultResponse.builder()
                .status(BAD_REQUEST.value())
                .error(details)
                .build();

        // log
        log.info("Error occurred. code:{}, details:{}", msg.getStatus(), msg.getData());

        // 返回响应
        return ResponseEntity
                .badRequest()
                .body(msg);
    }


    /**
     * 处理业务异常。
     *
     * @param ex {@link BizException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(BizException.class)
    public ResponseEntity<Object> handleBizException(final BizException ex) {
        final List<Object> details = new ArrayList<>();

        final ErrorDetail detail = ErrorDetail.builder()
                .type(ex.getCode())
                .description(ex.getMessage())
                .build();

        details.add(detail);

        final ResultResponse msg = ResultResponse.builder()
                .status(BAD_REQUEST.value())
                .error(details)
                .build();

        // log
        log.info("Error occurred. code:{}, details:{}", msg.getStatus(), msg.getData());

        return ResponseEntity
                .badRequest()
                .body(msg);
    }
}
