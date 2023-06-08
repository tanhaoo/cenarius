package com.th.cola.domain.order.pipeline;

import com.th.cenarius.commons.model.AbstractBizContext;
import com.th.cenarius.commons.model.IBizEnum;
import com.th.cenarius.commons.pattern.pipeline.filter.selector.FilterSelector;
import com.th.cola.domain.order.model.SalesOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Aaron
 * @Date: 2023/5/24
 */
public class SalesOrderContext extends AbstractBizContext {

    @Getter
    @Setter
    private SalesOrder salesOrder;

    @Getter
    @Setter
    private Boolean continueFlag = true;

    public SalesOrderContext(IBizEnum bizEnum, FilterSelector selector) {
        super(bizEnum, selector);
    }

    @Override
    public boolean continueChain() {
        return continueFlag;
    }
}
