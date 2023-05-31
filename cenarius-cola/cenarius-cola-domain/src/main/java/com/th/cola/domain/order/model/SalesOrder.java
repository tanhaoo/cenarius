package com.th.cola.domain.order.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aaron.tanhenkel.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrder {

    private String salesOrderId;

    private BigDecimal freight;

    private BigDecimal totalAmount;

    private String operateUser;

    private String description;

    private Boolean isUseCoupon;

    private List<SalesOrderItem> items;
}

