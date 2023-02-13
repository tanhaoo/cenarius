package com.th.test.pattern.strategy.template.component;

import com.th.test.pattern.strategy.template.FormItemConverter;
import com.th.test.pattern.strategy.template.FormItemTypeEnum;
import com.th.test.pattern.strategy.template.config.FormComponentProps;
import com.th.test.pattern.strategy.template.config.FormItemConfig;

import org.springframework.stereotype.Component;

/**
 * 下拉选择框的转换器
 *
 * @Author: Aaron
 * @Date: 2023/2/13
 */
@Component
public class DropdownSelectConverter extends FormItemConverter {
    @Override
    public FormItemTypeEnum getType() {
        return FormItemTypeEnum.DROPDOWN_SELECT;
    }

    @Override
    protected void afterPropsCreate(FormComponentProps props, FormItemConfig config) {
        props.setAutoWidth(false);

        if (config.getMultiple()) {
            props.setMode("multiple");
        }
    }
}
