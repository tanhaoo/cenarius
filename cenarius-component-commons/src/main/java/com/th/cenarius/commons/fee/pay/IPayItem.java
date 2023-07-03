package com.th.cenarius.commons.fee.pay;

import java.math.BigDecimal;

/**
 * @Author: Aaron
 * @Date: 2023/6/8
 */
public interface IPayItem {

    BigDecimal getMoney();

    IPayGroup getPayGroup();

    IPayType getPayType();
}
