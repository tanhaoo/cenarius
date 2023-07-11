package com.th.cola.client.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.th.cola.client.dto.CustomerAddCmd;
import com.th.cola.client.dto.CustomerListByNameQry;
import com.th.cola.client.dto.data.CustomerDTO;

public interface CustomerServiceI {

    Response addCustomer(CustomerAddCmd customerAddCmd);

    MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
