package com.hkgroup.sys.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @Description 转账案例
 * @Author hk
 * @Date 2024/8/30 17:32
 */
public interface AccountMapper {
    // 转⼊钱
    public void transferIn(@Param("name") String name, @Param("money") double money);

    // 转出钱
    public void transferOut(@Param("name") String name, @Param("money") double money);
}
