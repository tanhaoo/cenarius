package com.th.test.pattern.strategy.template.component;

import com.th.test.pattern.strategy.template.FormItemConverter;
import com.th.test.pattern.strategy.template.config.FormItemConfig;
import com.th.test.pattern.strategy.template.config.FormItemRule;

import java.util.List;

/**
 * 通用文本类转换器
 *
 * @Author: Aaron
 * @Date: 2023/2/13
 */
public abstract class CommonTextConverter extends FormItemConverter {

    @Override
    protected void afterRulesCreate(List<FormItemRule> rules, FormItemConfig config) {
        Integer minLength = config.getMinLength();

        if (minLength != null && minLength > 0) {
            FormItemRule minRule = new FormItemRule();
            minRule.setMin(minLength);
            minRule.setMessage("请至少输入 " + minLength + " 个字");

            rules.add(minRule);
        }

        Integer maxLength = config.getMaxLength();

        if (maxLength != null && maxLength > 0) {
            FormItemRule maxRule = new FormItemRule();
            maxRule.setMax(maxLength);
            maxRule.setMessage("请最多输入 " + maxLength + " 个字");

            rules.add(maxRule);
        }
    }
}