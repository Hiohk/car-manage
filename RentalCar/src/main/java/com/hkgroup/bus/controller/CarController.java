package com.hkgroup.bus.controller;

import com.hkgroup.bus.domain.Car;
import com.hkgroup.bus.service.CarService;
import com.hkgroup.bus.vo.CarVo;
import com.hkgroup.sys.constant.SysConstant;
import com.hkgroup.sys.utils.AppFileUtils;
import com.hkgroup.sys.utils.DataGridView;
import com.hkgroup.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.Date;

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

    @RequestMapping("addCar")
    public ResultObj addCar(CarVo carVo) {
        try {
            carVo.setCreatetime(new Date());
            if (!carVo.getCarimg().equals(SysConstant.DEFAULT_CAR_IMG)) {
                String filePath = AppFileUtils.updateFileName(carVo.getCarimg(), SysConstant.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }
            this.carService.addCar(carVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("deleteCar")
    public ResultObj deleteCar(String carnumber) {
        try {
            carService.deleteCar(carnumber);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 编辑车辆信息
     */
    @PostMapping("updateCar")
    public ResultObj updateCar(CarVo carVo) {
        try {
            String carimg = carVo.getCarimg();
            if (carimg.endsWith(SysConstant.FILE_UPLOAD_TEMP)) {
                String filePath = AppFileUtils.updateFileName(carVo.getCarimg(), SysConstant.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
                // 把原来的删除
                Car car = carService.queryByCarNumber(carVo.getCarnumber());
                AppFileUtils.removeFileByPath(car.getCarimg());
            }
            carService.updateCar(carVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 批量删除车辆
     */
    @PostMapping("deleteBatchCar")
    public ResultObj deleteBathCar(CarVo carVo) {
        try {
            carService.deleteBatchCar(carVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch(Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
