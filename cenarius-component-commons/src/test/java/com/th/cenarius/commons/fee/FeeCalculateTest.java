package com.th.cenarius.commons.fee;

import com.google.common.collect.Lists;

import com.th.cenarius.commons.Order;
import com.th.cenarius.commons.fee.config.StrategyFactoryConfig;
import com.th.cenarius.commons.fee.pay.IPayGroup;
import com.th.cenarius.commons.fee.pay.IPayItem;
import com.th.cenarius.commons.fee.pay.PayGroup;
import com.th.cenarius.commons.fee.rule.FeeRule;
import com.th.cenarius.commons.fee.rule.FeeRuleTypeEnum;
import com.th.cenarius.commons.fee.rule.MaxLimitRule;
import com.th.cenarius.commons.fee.rule.PlusRule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/6/12
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {StrategyFactoryConfig.class})
public class FeeCalculateTest {

    private Order salesOrder;

    @Resource(name = "feeCalculate")
    private FeeCalculate calculate;

    @BeforeEach
    public void init() {
        salesOrder = Order.builder()
                .orderId("Order001")
                .userId("006")
                .orderItemFee(new BigDecimal("70"))
                .userPlus(false)
                .build();
    }

    @Test
    public void testFeeCalculate() {
        List<FeeItem<Order>> feeItems = Lists.newArrayList();
        OrderFeeItem orderFeeItem = new OrderFeeItem(salesOrder);
        feeItems.add(orderFeeItem);

        Map<IFeeItemType, List<IPayItem>> map = calculate.payItemList(feeItems);

        Map<IFeeItemType, BigDecimal> calculateWaitPay = calculate.calculateWaitPay(feeItems);

        Map<IFeeItemType, List<IPayItem>> afterCalculatePay = calculate.payItemList(feeItems);

        calculateWaitPay.forEach((key, value) -> {
            System.err.println("待支付项：" + key);
            System.err.println("待支付费用：" + value);
        });
        System.err.println("抵扣详情: ");
        afterCalculatePay.forEach((key, value) -> {
            System.err.println("抵扣项：" + key.getName());
            value.forEach(item -> {
                System.err.println("支付组：" + item.getPayGroup());
                System.err.println("支付项：" + item.getPayType());
                System.err.println("抵扣金额：" + item.getMoney());
            });
        });
    }

    @Test
    public void testFee() {
        IPayGroup of = PayGroup.of(1);
        System.out.println(of);


    }
}
