package com.hkgroup.sys.controller;

import com.hkgroup.sys.service.IAccountService;
import com.hkgroup.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @Description 转账的控制器
 * @Author hk
 * @Date 2024/8/30 17:48
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService service;

    @RequestMapping("/transfer")
    @ResponseBody
    @Transactional
    public ResultObj accountTransfer(String inName, String outName, double money) {
        int status = service.updateTransfer(inName, outName, money);
        if (status > 0) {
            // 转账成功
            return ResultObj.STATUS_TRUE;
        } else {
            // 转账失败
            return ResultObj.STATUS_FALSE;
        }
    }
}
