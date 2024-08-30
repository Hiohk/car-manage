package com.hkgroup.sys.utils;

import com.hkgroup.sys.constant.SysConstant;
import lombok.Data;

/**
 * @version 1.0
 * @Description 封装服务器返回对象
 * @Author hk
 * @Date 2024/8/30 15:55
 */
@Data
public class ResultObj {
    private int code;
    private String msg;

    public ResultObj(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultObj(Integer code) {
        this.code = code;
    }

    /**
     * 添加成功
     */
    public static final ResultObj ADD_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.ADD_SUCCESS);

    /**
     * 添加失败
     */
    public static final ResultObj ADD_ERROR = new ResultObj(SysConstant.CODE_ERROR, SysConstant.ADD_ERROR);

    /**
     * 更新成功
     */
    public static final ResultObj UPDATE_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.UPDATE_SUCCESS);

    /**
     * 更新失败
     */
    public static final ResultObj UPDATE_ERROR = new ResultObj(SysConstant.CODE_ERROR, SysConstant.UPDATE_ERROR);

    /**
     * 删除成功
     */
    public static final ResultObj DELETE_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.DELETE_SUCCESS);

    /**
     * 删除失败
     */
    public static final ResultObj DELETE_ERROR = new ResultObj(SysConstant.CODE_ERROR, SysConstant.DELETE_ERROR);

    /**
     * 重置成功
     */
    public static final ResultObj RESET_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.RESET_SUCCESS);

    /**
     * 重置失败
     */
    public static final ResultObj RESET_ERROR = new ResultObj(SysConstant.CODE_ERROR, SysConstant.RESET_ERROR);

    /**
     * 分配成功
     */
    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.DISPATCH_SUCCESS);

    /**
     * 分配失败
     */
    public static final ResultObj DISPATCH_ERROR = new ResultObj(SysConstant.CODE_ERROR, SysConstant.DISPATCH_ERROR);

    /**
     * 状态码0 成功
     */
    public static final ResultObj STATUS_TRUE = new ResultObj(SysConstant.CODE_SUCCESS);


    /**
     * 状态码-1 失败
     */
    public static final ResultObj STATUS_FALSE = new ResultObj(SysConstant.CODE_ERROR);
}
