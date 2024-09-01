package com.hkgroup.sys.service.impl;

import com.hkgroup.sys.mapper.AccountMapper;
import com.hkgroup.sys.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version 1.0
 * @Description
 * @Author hk
 * @Date 2024/8/30 17:41
 */

@Service
@Transactional //事务控制
public class AccountServiceImpl implements AccountService {
    @Autowired
    public AccountMapper mapper;

    @Override
    public int updateTransfer(String inName, String outName, double money) {
        try {
            // 转入
            mapper.transferIn(inName, money);

            // 转出
            mapper.transferOut(outName, money);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
