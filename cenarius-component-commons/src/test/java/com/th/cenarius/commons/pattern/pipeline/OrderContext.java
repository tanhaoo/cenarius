package com.th.cenarius.commons.pattern.pipeline;

import com.th.cenarius.commons.Order;
import com.th.cenarius.commons.model.AbstractBizContext;
import com.th.cenarius.commons.model.BizEnum;
import com.th.cenarius.commons.pattern.pipeline.filter.selector.FilterSelector;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Aaron
 * @Date: 2023/5/18
 */
public class OrderContext extends AbstractBizContext {

    @Getter
    @Setter
    private Order orderModel;

    public OrderContext(BizEnum bizEnum, FilterSelector selector) {
        super(bizEnum, selector);
    }

    @Override
    public boolean continueChain() {
        return true;
    }
}
