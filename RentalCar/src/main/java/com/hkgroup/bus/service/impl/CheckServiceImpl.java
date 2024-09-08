package com.hkgroup.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkgroup.bus.domain.Car;
import com.hkgroup.bus.domain.Check;
import com.hkgroup.bus.domain.Customer;
import com.hkgroup.bus.domain.Rent;
import com.hkgroup.bus.mapper.CarMapper;
import com.hkgroup.bus.mapper.CheckMapper;
import com.hkgroup.bus.mapper.CustomerMapper;
import com.hkgroup.bus.mapper.RentMapper;
import com.hkgroup.bus.service.CheckService;
import com.hkgroup.bus.vo.CarVo;
import com.hkgroup.bus.vo.CheckVo;
import com.hkgroup.bus.vo.RentVo;
import com.hkgroup.sys.constant.SysConstant;
import com.hkgroup.sys.domain.User;
import com.hkgroup.sys.utils.DataGridView;
import com.hkgroup.sys.utils.RandomUtils;
import com.hkgroup.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //查询出租车
        Rent rent = rentMapper.queryRentById(rentid);
        //查询客户
        Customer customer = customerMapper.selectByPrimaryKey(rent.getIdentity());

        //查询车辆信息
        Car car = carMapper.selectByPrimaryKey(rent.getCarnumber());

        //组装check对象
        Check check = new Check();

        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstant.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());
        Map<String, Object> map = new HashMap<>();
        map.put("rent", rent);
        map.put("customer", customer);
        map.put("car", car);
        map.put("check", check);
        return map;
    }

    /**
     * 保存检查单数据
     * @param checkVo
     */
    @Override
    public void addCheck(CheckVo checkVo) {
        checkMapper.insertSelective(checkVo);
        //更改出租单的状态
        Rent rent = rentMapper.queryRentById(checkVo.getRentid());
        RentVo rentVo = new RentVo();
        rentVo.setRentid(rent.getRentid());
        rentVo.setRentflag(SysConstant.RENT_BACK_TRUE);

        rentMapper.updateByPrimaryKeySelective(rentVo);
        //更改汽⻋的状态
        Car car = carMapper.selectByPrimaryKey(rent.getCarnumber());
        //更改汽⻋状态为未出租
        CarVo carVo = new CarVo();
        carVo.setCarnumber(car.getCarnumber());
        carVo.setIsrenting(SysConstant.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(carVo);
    }

    /**
     * 查询所有检查单
     * @param checkVo
     * @return
     */
    @Override
    public DataGridView queryAllCheck(CheckVo checkVo) {
        Page<Object> page =
                PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        List<Check> data = checkMapper.queryAllCheck(checkVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 更新检查单
     * @param checkVo
     */
    @Override
    public void updateCheck(CheckVo checkVo) {
        this.checkMapper.updateByPrimaryKeySelective(checkVo);
    }

    /**
     * 删除检查单
     * @param checkVo
     */
    @Override
    public void deleteCheck(CheckVo checkVo) {
        this.checkMapper.deleteByPrimaryKey(checkVo.getCheckid());
    }

    /**
     * 批量删除检查单
     * @param ids
     */
    @Override
    public void deleteBatchCheck(String[] ids) {
        for (String id : ids) {
            this.checkMapper.deleteByPrimaryKey(id);
        }
    }
}
