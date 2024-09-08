package com.hkgroup.bus.mapper;

import com.hkgroup.bus.domain.Customer;
import com.hkgroup.bus.vo.CustomerVo;

import java.util.List;

public interface CustomerMapper {
    /**
     * 查询
     */
    List<Customer> queryAllCustomer(Customer customer);

    /**
     * 添加
     */
    int insertSelective(Customer customer);

    void deleteCustomer(String identity);
    void updateCustomer(Customer customer);
    Customer selectByPrimaryKey(String identity);
}
