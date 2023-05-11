package com.th.cola.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/5/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderAddCmd {

    private String description;

    private BigDecimal totalAmount;

    private String operateUser;
}
