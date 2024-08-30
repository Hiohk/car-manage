package com.hkgroup.sys.constant;

/**
 * @version 1.0
 * @Description 常量接口
 * @Author hk
 * @Date 2024/8/30 15:41
 */
public interface SysConstant {
    /**
     * 用户登录相关常量
     *
     * @param
     * @return
     * @date 2024/8/30 15:48
     */
    String USER_LOGIN_ERROR_MSG = "用户名或密码错误";
    String USER_LOGIN_CODE_ERROR_MSG = "验证码错误";


    /**
     * 可用状态
     *
     * @param
     * @return
     * @date 2024/8/30 15:43
     */
    Integer AVAILABLE_TRUE = 1;
    Integer AVAILABLE_FALSE = 0;


    /**
     * 用户类型
     *
     * @param
     * @return
     * @date 2024/8/30 15:44
     */
    Integer USER_TYPE_SUPER = 1; // 超级管理员
    Integer USER_TYPE_NORMAL = 2; // 普通用户


    /**
     * 是否展开
     *
     * @param
     * @return
     * @date 2024/8/30 15:45
     */
    Integer SPREAD_TRUE = 1;
    Integer SPREAD_FALSE = 0;


    /**
     * 操作状态
     */
    String ADD_SUCCESS = "添加成功";
    String ADD_ERROR = "添加失败";
    String UPDATE_SUCCESS = "修改成功";
    String UPDATE_ERROR = "修改失败";
    String DELETE_SUCCESS = "删除成功";
    String DELETE_ERROR = "删除失败";
    String RESET_SUCCESS = "重置成功";
    String RESET_ERROR = "重置失败";
    String DISPATCH_SUCCESS = "分配成功";
    String DISPATCH_ERROR = "分配失败";
    Integer CODE_SUCCESS = 0; //操作成功
    Integer CODE_ERROR = -1; //操作失败

    /**
     * 公⽤常量
     */
    Integer CODE_ZERO = 0;
    Integer CODE_ONE = 1;
    Integer CODE_TWO = 2;
    Integer CODE_THREE = 3;


    /**
     * ⽤户默认密码
     */
    String USER_DEFAULT_PWD = "123456";

    /**
     * 临时⽂件标记
     */
    String FILE_UPLOAD_TEMP = "_temp";


    /**
     * 默认图⽚地址
     */
    String DEFAULT_CAR_IMG = "images/default_car_image.jpg";


    /**
     * 单号的前缀
     */
    String CAR_ORDER_CZ = "CZ"; //出租车辆的订单号前缀
    String CAR_ORDER_JC = "JC"; //检查单的单号前缀


    /**
     * 归还状态
     */
    Integer RENT_BACK_FALSE = 0; //未归还
    Integer RENT_BACK_TRUE = 1; //已归还


    /**
     * 出租状态
     */
    Integer RENT_CAR_TRUE = 1; //已出租
    Integer RENT_CAR_FALSE = 0; //未出租
}
