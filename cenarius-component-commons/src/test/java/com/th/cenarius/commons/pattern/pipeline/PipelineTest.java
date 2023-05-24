package com.th.cenarius.commons.pattern.pipeline;

import com.th.cenarius.commons.Order;
import com.th.cenarius.commons.model.BizEnum;
import com.th.cenarius.commons.pattern.pipeline.filter.CalculatePromoFilter;
import com.th.cenarius.commons.pattern.pipeline.filter.CheckBizFilter;
import com.th.cenarius.commons.pattern.pipeline.filter.SaveOrderFilter;
import com.th.cenarius.commons.pattern.pipeline.filter.SaveOrderLogFilter;
import com.th.cenarius.commons.pattern.pipeline.filter.selector.FilterSelector;
import com.th.cenarius.commons.pattern.pipeline.filter.selector.LocalListBasedFilterSelector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PipelineConfig.class})
public class PipelineTest {


    @Resource
    private FilterChainPipeline orderPipeline;

    List<Order> orders = new ArrayList<>();

    @BeforeEach
    public void initData() {
        for (int i = 0; i < 50; i++) {
            orders.add(new Order(String.valueOf(i), BigDecimal.valueOf(new Random().nextDouble() * 100), "order detail " + i));
        }
    }


    @Test
    public void testOrderPipeline() {
        orders.forEach(this::createOrder);
    }

    public void createOrder(Order order) {
        FilterSelector orderSelector = getOrderSelector();
        OrderContext orderContext = new OrderContext(BizEnum.BIZ_ORDER, orderSelector);
        orderContext.setOrderModel(order);

        orderPipeline.getFilterChain().handle(orderContext);
    }


    public FilterSelector getOrderSelector() {
        LocalListBasedFilterSelector selector = new LocalListBasedFilterSelector();
        selector.addFilter(CalculatePromoFilter.class.getSimpleName());
        selector.addFilter(CheckBizFilter.class.getSimpleName());
        selector.addFilter(SaveOrderFilter.class.getSimpleName());
        selector.addFilter(SaveOrderLogFilter.class.getSimpleName());

        return selector;
    }
}
