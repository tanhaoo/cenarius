package com.th.cenarius.commons.fee.config;

import com.th.cenarius.commons.fee.CalculatorStrategy;
import com.th.cenarius.commons.fee.FeeCalculate;
import com.th.cenarius.commons.fee.calculator.MaxLimitCalculator;
import com.th.cenarius.commons.fee.calculator.PlusRuleCalculator;
import com.th.cenarius.commons.fee.rule.FeeRule;
import com.th.cenarius.commons.fee.rule.FeeRuleTypeEnum;
import com.th.cenarius.commons.fee.rule.IFeeRuleType;
import com.th.cenarius.commons.fee.rule.MaxLimitRule;
import com.th.cenarius.commons.fee.rule.PlusRule;
import com.th.cenarius.commons.pattern.strategy.StrategyFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Aaron
 * @Date: 2023/6/26
 */
@Configuration
public class StrategyFactoryConfig {

    @Bean
    public StrategyFactory<IFeeRuleType, CalculatorStrategy> feeCalculateStrategyFactory() {
        return new StrategyFactory<>(CalculatorStrategy.class);
    }

    @Bean
    public PlusRuleCalculator plusRuleCalculator() {
        return new PlusRuleCalculator(null, FeeRuleTypeEnum.PLUS_RULE, null);
    }

    @Bean
    public MaxLimitCalculator maxLimitRuleCalculator() {
        return new MaxLimitCalculator(null, FeeRuleTypeEnum.MAX_LIMIT, null);
    }

    @Bean
    public PlusRule plusRule() {
        return new PlusRule(new BigDecimal(".8"), FeeRuleTypeEnum.PLUS_RULE, 2);
    }

    @Bean
    public MaxLimitRule maxLimitRule() {
        return new MaxLimitRule(new BigDecimal("50"), FeeRuleTypeEnum.MAX_LIMIT, 1);
    }

    @Bean
    public FeeCalculate feeCalculate(StrategyFactory<IFeeRuleType, CalculatorStrategy> calculatorFactory, List<FeeRule> rules) {
        FeeCalculate calculate = null;
        List<FeeRule> sortRules = rules.stream().sorted(Comparator.comparing(FeeRule::getOrder)).collect(Collectors.toList());

        for (FeeRule rule : sortRules) {
            CalculatorStrategy strategy = calculatorFactory.getStrategy(rule.getRuleType());
            calculate = strategy.getCalculator(calculate, rule);
        }
        return calculate;
    }
}
