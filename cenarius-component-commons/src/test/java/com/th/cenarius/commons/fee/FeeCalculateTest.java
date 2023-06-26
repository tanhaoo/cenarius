package com.th.cenarius.commons.fee;

import com.google.common.collect.Lists;

import com.th.cenarius.commons.Order;
import com.th.cenarius.commons.fee.rule.FeeRule;
import com.th.cenarius.commons.fee.rule.FeeRuleType;
import com.th.cenarius.commons.fee.rule.MaxLimitRule;
import com.th.cenarius.commons.fee.rule.PlusRule;
import com.th.cenarius.commons.pattern.pipeline.PipelineConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Aaron
 * @Date: 2023/6/12
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PipelineConfig.class})
public class FeeCalculateTest {

    Order salesOrder;

    @BeforeEach
    public void init() {
        salesOrder = Order.builder()
                .orderId("Order001")
                .userId("006")
                .orderItemFee(new BigDecimal("70"))
                .build();
    }

    @Test
    public void testFee() {
        List<FeeRule> ruleList = Lists.newArrayList();
        PlusRule plusRule = new PlusRule(new BigDecimal(".8"), FeeRuleType.PLUS_RULE, 2);
        MaxLimitRule maxLimitRule = new MaxLimitRule(new BigDecimal("50"), FeeRuleType.MAX_LIMIT, Integer.MAX_VALUE);
        ruleList.add(plusRule);
        ruleList.add(maxLimitRule);

        List<FeeItem<Order>> feeItems = Lists.newArrayList();
        OrderFeeItem orderFeeItem = new OrderFeeItem(salesOrder);
        feeItems.add(orderFeeItem);


    }
}
