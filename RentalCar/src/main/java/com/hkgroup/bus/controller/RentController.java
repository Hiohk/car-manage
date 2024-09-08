package com.hkgroup.bus.controller;

import com.hkgroup.bus.domain.Customer;
import com.hkgroup.bus.service.CustomerService;
import com.hkgroup.bus.service.RentService;
import com.hkgroup.bus.vo.RentVo;
import com.hkgroup.sys.constant.SysConstant;
import com.hkgroup.sys.domain.User;
import com.hkgroup.sys.utils.DataGridView;
import com.hkgroup.sys.utils.RandomUtils;
import com.hkgroup.sys.utils.ResultObj;
import com.hkgroup.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("rent")
public class RentController {
    @Autowired
    private RentService rentService;

    @Autowired
    private CustomerService customerService;

    /**
     * 检查身份证号是否存在
     */
    @RequestMapping("checkCustomerExit")
    public ResultObj checkCustomerExit(RentVo rentVo) {
        Customer customer = customerService.queryCustomerByIdentity(rentVo.getIdentity());
        if (customer != null) {
            return ResultObj.STATUS_TRUE;
        } else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 初始化添加出租单的表单的数据
     *
     * @param rentVo
     * @return
     */
    @RequestMapping("initRentFrom")
    public RentVo initRenyFrom(RentVo rentVo) {
        //⽣成出租单号
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstant.CAR_ORDER_CZ));
        //设置起租时间
        rentVo.setBegindate(new Date());
        //设置操作员
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        rentVo.setOpername(user.getRealname());
        return rentVo;
    }

    /**
     * 保存出租
     */
    @PostMapping("saveRent")
    public ResultObj saveRent(RentVo rentVo) {
        try {
            rentVo.setCreatetime(new Date());
            rentVo.setRentflag(SysConstant.RENT_BACK_FALSE);
            rentService.addRent(rentVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_SUCCESS;
        }
    }

    /**
     * 查询出租单
     */
    @RequestMapping("loadAllRent")
    public DataGridView loadAllRent(RentVo rentVo) {
        return rentService.queryAllRent(rentVo);
    }

    /**
     * 修改出租单信息
     */
    @RequestMapping("updateRent")
    public ResultObj updateRent(RentVo rentVo) {
        try {
            rentService.updateRent(rentVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch(Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除出租单信息
     */
    @RequestMapping("deleteRent")
    public ResultObj deleteRent(RentVo rentVo) {
        try {
            rentService.deleteRent(rentVo);
            return ResultObj.DELETE_SUCCESS;
        } catch(Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
