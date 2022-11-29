package com.th.cenarius.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: Aaron
 * @Date: 2022/11/23
 */
@Data
@JsonPropertyOrder({"target", "type", "description"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Builder
public class ErrorDetail {

    /**
     * 参数
     */
    private String target;

    private Object value;

    /**
     * 错误类型
     */
    private String type;

    /**
     * 原因
     */
    private String description;
}

