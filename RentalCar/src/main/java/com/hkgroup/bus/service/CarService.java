package com.hkgroup.bus.service;

import com.hkgroup.bus.domain.Car;
import com.hkgroup.bus.vo.CarVo;
import com.hkgroup.sys.utils.DataGridView;

public interface CarService {
    /**
     * 查询所有车辆
     */
     public DataGridView queryAllCar(CarVo carVo);

     /**
      * 新增车辆
      */
     void addCar(CarVo carVo);

     void deleteCar(String carnumber);
    /**
     * 根据车牌号查询
     */
     Car queryByCarNumber(String carnumber);

    /**
     * 修改车辆
     */
    void updateCar(CarVo carVo);

    /**
     * 批量删除车辆
     */
    void deleteBatchCar(String[] ids);
}
