package com.hkgroup.bus.mapper;

import com.hkgroup.bus.domain.Check;
import com.hkgroup.bus.vo.CheckVo;

import java.util.List;

public interface CheckMapper {
    void insertSelective(CheckVo checkVo);
    List<Check> queryAllCheck(CheckVo checkVo);

    int updateByPrimaryKeySelective(Check record);

    int deleteByPrimaryKey(String checkid);
}
