package com.hkgroup.bus.service;

import com.hkgroup.bus.domain.Customer;
import com.hkgroup.bus.vo.CustomerVo;
import com.hkgroup.sys.utils.DataGridView;

public interface CustomerService {
    public DataGridView queryAllCustomer(CustomerVo customerVo);

    void addCustomer(CustomerVo customerVo);

    void deleteCustomer(String identity);
    void updateCustomer(CustomerVo customerVo);
    void deleteBatchCustomer(String[] identities);
    Customer queryCustomerByIdentity(String identity);
}
