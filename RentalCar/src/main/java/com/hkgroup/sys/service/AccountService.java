package com.hkgroup.sys.service;

/**
 * @version 1.0
 * @Description
 * @Author hk
 * @Date 2024/8/30 17:38
 */
public interface AccountService {
    // 转战
    public int updateTransfer(String inName,String outName,double money);
}
