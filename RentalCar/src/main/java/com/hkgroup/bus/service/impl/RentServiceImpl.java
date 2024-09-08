package com.hkgroup.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkgroup.bus.domain.Car;
import com.hkgroup.bus.domain.Rent;
import com.hkgroup.bus.mapper.CarMapper;
import com.hkgroup.bus.mapper.RentMapper;
import com.hkgroup.bus.service.RentService;
import com.hkgroup.bus.vo.CarVo;
import com.hkgroup.bus.vo.RentVo;
import com.hkgroup.sys.constant.SysConstant;
import com.hkgroup.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public void addRent(RentVo rentVo) {
        rentMapper.insertSelective(rentVo);
        CarVo vo = new CarVo();
        vo.setCarnumber(rentVo.getCarnumber());
        vo.setIsrenting(SysConstant.RENT_BACK_TRUE);
        carMapper.updateByPrimaryKeySelective(vo);
    }

    @Override
    public DataGridView queryAllRent(RentVo rentVo) {
        Page<Object> page = PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());
        List<Rent> data = rentMapper.queryAllRent(rentVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void updateRent(RentVo rentVo) {
        rentMapper.updateByPrimaryKeySelective(rentVo);
    }

    @Override
    public void deleteRent(RentVo rentVo) {
        //更改汽⻋状态，将已出租的状态转换成未出租的状
        CarVo carVo = new CarVo();
        carVo.setCarnumber(rentVo.getCarnumber());
        //转换成未出租的状态
        carVo.setIsrenting(SysConstant.RENT_CAR_FALSE);
        rentMapper.deleteByPrimaryKey(rentVo.getRentid());
        carMapper.updateByPrimaryKeySelective(carVo);
    }

    @Override
    public Rent queryRentByRentId(String rentid) {
        return rentMapper.queryRentById(rentid);
    }
}
