package com.th.cola.domain.customer.gateway;

import com.th.cola.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
