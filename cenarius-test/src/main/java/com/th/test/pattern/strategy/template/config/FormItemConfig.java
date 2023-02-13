package com.th.test.pattern.strategy.template.config;

import com.th.test.pattern.strategy.template.FormItemTypeEnum;

import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2023/2/13
 */
@Data
public class FormItemConfig {

    private FormItemTypeEnum type;

    private String code;

    private String component;

    private String title;

    private String placeholder;

    private Boolean readOnly;

    private Boolean required;

    private Boolean multiple;

    private Integer minLength;

    private Integer maxLength;
}
