package com.th.cola.domain.customer.gateway;

import com.th.cola.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
