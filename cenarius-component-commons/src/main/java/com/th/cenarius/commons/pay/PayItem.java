package com.th.cenarius.commons.pay;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/6/8
 */
public interface PayItem {

    BigDecimal getMoney();

//    PayGroup getPayGroup();

    PayType getPayType();
}
