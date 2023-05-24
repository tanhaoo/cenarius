package com.th.test.pattern.template.config;

import java.util.List;

import lombok.Data;

/**
 * @Author: Aaron
 * @Date: 2023/2/13
 */
@Data
public class FormItem {

    private String code;

    private String component;

    private String title;

    private FormComponentProps componentProps;

    private List<FormItemRule> rules;
}
