package com.th.test.pattern.template.component;

import com.th.test.pattern.template.FormItemTypeEnum;

import org.springframework.stereotype.Component;

/**
 * 多行文本框的转换器
 *
 * @Author: Aaron
 * @Date: 2023/2/13
 */
@Component
public class TextAreaConverter extends CommonTextConverter {

    @Override
    public FormItemTypeEnum getType() {
        return FormItemTypeEnum.TEXT_AREA;
    }
}
