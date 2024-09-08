package com.hkgroup.bus.mapper;

import com.hkgroup.bus.domain.Rent;
import com.hkgroup.bus.vo.RentVo;

import java.util.List;

public interface RentMapper {
    void insertSelective(RentVo rentVo);
    /**
     * 查询
     */
    List<Rent> queryAllRent(Rent rent);
    int updateByPrimaryKeySelective(Rent rent);
    void deleteByPrimaryKey(String rentid);
    Rent queryRentById(String rentid);
}
