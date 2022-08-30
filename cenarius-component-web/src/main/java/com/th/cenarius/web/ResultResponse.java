package com.th.cenarius.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @Author: Aaron
 * @Date: 2022/3/7
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "data"})
@Data
//@NoArgsConstructor
//@Accessors(chain = true)
// fluent = true 慎用(省略get set直接显示属性名)，很多框架都是用get set方法的，会有问题
@SuperBuilder
public class ResultResponse {
    /**
     * 状态码。使用Http协议的状态码。
     */
    private Integer status;

    /**
     * 响应内容
     */
    private Object data;
}
