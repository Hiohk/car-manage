package com.hkgroup.bus.mapper;

import com.hkgroup.bus.domain.Car;
import com.hkgroup.bus.vo.CarVo;

import java.util.List;

public interface CarMapper {
    List<Car> queryAllCar(CarVo carVo);
}
