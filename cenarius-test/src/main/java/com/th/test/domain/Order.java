package com.th.test.domain;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @NotNull
    public Integer id;

    @NotEmpty
    public List<OrderItem> items;

    public OrderPrice orderPrice;

    public List<Integer> codes;

    @Pattern(regexp = "[-&]{0,1}")
    private String testVal;
}
