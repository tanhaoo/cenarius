package com.th.test.pattern.template;

import com.th.cenarius.commons.pattern.strategy.Strategy;
import com.th.test.pattern.template.config.FormComponentProps;
import com.th.test.pattern.template.config.FormItem;
import com.th.test.pattern.template.config.FormItemConfig;
import com.th.test.pattern.template.config.FormItemRule;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 以标单项为例，将输入的表单配置转为表单项
 * 通用步骤分为三步
 * 1 创建表单项，并设置通用的表单项属性，然后再对不同表单项的特殊属性进行处理
 * 2 创建组件属性，处理通用的组件属性，然后再对不同组件的特殊属性进行处理
 * 3 创建约束规则，处理通用的约束规则，然后再对不同表单项的特性约束规则进行处理
 *
 * @Author: Aaron
 * @Date: 2023/2/13
 */
public abstract class FormItemConverter implements Strategy<FormItemTypeEnum> {

    @Override
    public FormItemTypeEnum getId() {
        return getType();
    }

    /**
     * 子类可处理的表单项类型
     *
     * @return {@link FormItemTypeEnum} instance
     */
    public abstract FormItemTypeEnum getType();

    /**
     * 将输入的配置转变为表单项的操作流程
     *
     * @param config 前端输入的配置
     * @return 表单项
     */
    public final FormItem convert(FormItemConfig config) {
        FormItem item = createItem(config);
        // 表单项创建完成之后，子类如果需要特殊处理，可覆写该方法
        afterItemCreate(item, config);

        FormComponentProps props = createComponentProps(config);
        item.setComponentProps(props);

        // 组件属性创建完成之后，子类如果需要特殊处理，可覆写该方法
        afterPropsCreate(props, config);

        List<FormItemRule> rules = createRules(config);
        item.setRules(rules);

        // 约束规则创建完成之后，子类如果需要特殊处理，可覆写该方法
        afterRulesCreate(rules, config);

        return item;
    }

    /**
     * 共用逻辑：创建表单项、设置通用的表单项属性
     */
    private FormItem createItem(FormItemConfig config) {
        FormItem formItem = new FormItem();

        formItem.setCode(config.getCode());
        formItem.setTitle(config.getTitle());
        formItem.setComponent(config.getComponent());

        return formItem;
    }

    /**
     * 表单项创建完成之后，子类如果需要特殊处理，可覆写该方法
     */
    protected void afterItemCreate(FormItem item, FormItemConfig config) {
    }

    /**
     * 共用逻辑：创建组件属性、设置通用的组件属性
     */
    private FormComponentProps createComponentProps(FormItemConfig config) {
        FormComponentProps props = new FormComponentProps();

        if (config.getReadOnly()) {
            props.setReadOnly(true);
        }

        if (StringUtils.isNotBlank(config.getPlaceholder())) {
            props.setPlaceholder(config.getPlaceholder());
        }

        return props;
    }

    /**
     * 组件属性创建完成之后，子类如果需要特殊处理，可覆写该方法
     */
    protected void afterPropsCreate(FormComponentProps props, FormItemConfig config) {
    }


    /**
     * 共用逻辑：创建约束规则、设置通用的约束规则
     */
    private List<FormItemRule> createRules(FormItemConfig config) {
        List<FormItemRule> rules = new ArrayList<>(4);

        if (config.getRequired()) {
            FormItemRule requiredRule = new FormItemRule();
            requiredRule.setRequired(true);
            requiredRule.setMessage("请输入" + config.getTitle());

            rules.add(requiredRule);
        }

        return rules;
    }

    /**
     * 约束规则创建完成之后，子类如果需要特殊处理，可覆写该方法
     */
    protected void afterRulesCreate(List<FormItemRule> rules, FormItemConfig config) {
    }

}
