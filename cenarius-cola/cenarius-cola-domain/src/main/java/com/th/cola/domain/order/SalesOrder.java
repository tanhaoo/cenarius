package com.th.cola.domain.order;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrder {
    private String salesOrderId;

    private String description;

    private BigDecimal totalAmount;

    private String operateUser;
}
