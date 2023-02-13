package com.th.test.pattern.strategy.template.config;

import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2023/2/13
 */
@Data
public class FormItemRule {

    private Boolean required;

    private String message;

    private Integer max;

    private Integer min;
}
