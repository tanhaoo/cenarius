package com.th.cola.app.customer;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.th.cola.app.customer.executor.query.CustomerListByNameQryExe;
import com.th.cola.client.api.CustomerServiceI;
import com.th.cola.client.dto.CustomerAddCmd;
import com.th.cola.client.dto.CustomerListByNameQry;
import com.th.cola.client.dto.data.CustomerDTO;

import org.springframework.stereotype.Service;

import com.th.cola.app.customer.executor.CustomerAddCmdExe;

import javax.annotation.Resource;


@Service
@CatchAndLog
public class CustomerServiceImpl implements CustomerServiceI {

    @Resource
    private CustomerAddCmdExe customerAddCmdExe;

    @Resource
    private CustomerListByNameQryExe customerListByNameQryExe;

    public Response addCustomer(CustomerAddCmd customerAddCmd) {
        return customerAddCmdExe.execute(customerAddCmd);
    }

    @Override
    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry) {
        return customerListByNameQryExe.execute(customerListByNameQry);
    }

}