package com.hkgroup.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("desk")
public class DeskController {
    /**
     * 跳转到工作台页面
     */
    @RequestMapping("toDeskManager")
    public String toDeskManger() {
        return "system/main/deskManger";
    }
}
