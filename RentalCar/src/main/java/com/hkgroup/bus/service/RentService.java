package com.hkgroup.bus.service;

import com.hkgroup.bus.domain.Rent;
import com.hkgroup.bus.vo.RentVo;
import com.hkgroup.sys.utils.DataGridView;

public interface RentService {
    void addRent(RentVo rentVo);

    /**
     * 查询
     */
    DataGridView queryAllRent(RentVo rentVo);

    /**
     * 修改出租单
     */
    void updateRent(RentVo rentVo);

    /**
     * 删除出租单
     * @param
     */
    void deleteRent(RentVo rentVo);

    Rent queryRentByRentId(String rentId);
}
