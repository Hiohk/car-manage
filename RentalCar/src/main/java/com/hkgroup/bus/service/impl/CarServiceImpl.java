package com.hkgroup.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkgroup.bus.domain.Car;
import com.hkgroup.bus.mapper.CarMapper;
import com.hkgroup.bus.service.CarService;
import com.hkgroup.bus.vo.CarVo;
import com.hkgroup.sys.constant.SysConstant;
import com.hkgroup.sys.utils.AppFileUtils;
import com.hkgroup.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public DataGridView queryAllCar(CarVo carVo) {
        Page<Object> page = PageHelper.startPage(carVo.getPage(), carVo.getLimit());
        List<Car> data = carMapper.queryAllCar(carVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addCar(CarVo carVo) {
        this.carMapper.insertSelective(carVo);
    }

    @Override
    public void deleteCar(String carnumber) {
        Car car = carMapper.selectByPrimaryKey(carnumber);
        if(!car.getCarimg().equals(SysConstant.DEFAULT_CAR_IMG)) {
            AppFileUtils.deleteFileUsePath(car.getCarimg());
        }
        this.carMapper.deleteByPrimaryKey(carnumber);
    }

    @Override
    public Car queryByCarNumber(String carnumber) {
        return carMapper.selectByPrimaryKey(carnumber);
    }

    @Override
    public void updateCar(CarVo carVo) {
        carMapper.updateByPrimaryKeySelective(carVo);
    }

    @Override
    public void deleteBatchCar(String[] ids) {
        for(String id : ids) {
            deleteCar(id);
        }
    }

}
