package com.hkgroup.bus.service;

import com.hkgroup.bus.vo.CarVo;
import com.hkgroup.sys.utils.DataGridView;

public interface CarService {
    /**
     * 查询所有车辆
     */
     public DataGridView queryAllCar(CarVo carVo);
}
