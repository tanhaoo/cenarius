package com.th.test.pattern.strategy;

import com.th.cenarius.commons.pattern.strategy.StrategyFactory;
import com.th.test.pattern.strategy.config.StrategyFactoryConfig;
import com.th.test.pattern.template.FormItemConverter;
import com.th.test.pattern.template.FormItemTypeEnum;
import com.th.test.pattern.template.component.DropdownSelectConverter;
import com.th.test.pattern.template.component.TextAreaConverter;
import com.th.test.pattern.template.component.TextInputConverter;
import com.th.test.pattern.template.config.FormItem;
import com.th.test.pattern.template.config.FormItemConfig;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/2/13
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {StrategyFactoryConfig.class, DropdownSelectConverter.class, TextAreaConverter.class, TextInputConverter.class})
public class FormItemConverterTest {


    @Resource
    private StrategyFactory<FormItemTypeEnum, FormItemConverter> converter;

    @Test
    public void test() {
        // 模版模式与策略工厂的结合
        // 继承策略接口，定义抽象类，来把公用步骤统一，然后子类分别实现自己的特定规则
        FormItemConfig config = new FormItemConfig();
        config.setCode("001");
        config.setTitle("title1");
        config.setComponent("dropdown select");
        config.setType(FormItemTypeEnum.DROPDOWN_SELECT);

        config.setReadOnly(true);
        config.setPlaceholder("placeholder");

        config.setRequired(true);

        config.setMultiple(true);

        FormItemConverter strategy = converter.getStrategy(FormItemTypeEnum.DROPDOWN_SELECT);
        FormItem item = strategy.convert(config);
        System.err.println(item);
    }
}
