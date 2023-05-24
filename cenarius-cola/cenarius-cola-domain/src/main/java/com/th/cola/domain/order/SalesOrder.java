package com.th.cola.domain.order;

import com.th.cenarius.commons.model.AbstractBizContext;
import com.th.cenarius.commons.model.BizEnum;
import com.th.cenarius.commons.pattern.pipeline.filter.selector.FilterSelector;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;


public class SalesOrder extends AbstractBizContext {
    private String salesOrderId;

    private String description;

    private BigDecimal totalAmount;

    private String operateUser;

    public SalesOrder(BizEnum bizEnum, FilterSelector selector) {
        super(bizEnum, selector);
    }

    @Override
    public boolean continueChain() {
        return false;
    }
}
