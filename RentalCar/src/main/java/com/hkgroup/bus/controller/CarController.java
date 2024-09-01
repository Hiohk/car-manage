package com.hkgroup.bus.controller;

import com.hkgroup.bus.service.CarService;
import com.hkgroup.bus.vo.CarVo;
import com.hkgroup.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;
    /**
     * 加载车辆列表
     */

    @RequestMapping("loadAllCar")
    public DataGridView loadAllCar(CarVo carVo) {
        return this.carService.queryAllCar(carVo);
    }
}
