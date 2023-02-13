package com.th.test.pattern.strategy.template.component;

import com.th.test.pattern.strategy.template.FormItemTypeEnum;

import org.springframework.stereotype.Component;

/**
 * 单行文本框的转换器
 *
 * @Author: Aaron
 * @Date: 2023/2/13
 */
@Component
public class TextInputConverter extends CommonTextConverter {

    @Override
    public FormItemTypeEnum getType() {
        return FormItemTypeEnum.TEXT_INPUT;
    }
}
