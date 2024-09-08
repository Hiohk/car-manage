package com.hkgroup.stat.controller;

import com.hkgroup.bus.service.CustomerService;
import com.hkgroup.bus.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("stat")
@Controller
public class StatController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RentService rentService;

    /**
     * 导出出租单
     */
    @RequestMapping("exportRent")
    public ResponseEntity<Object> exportRent(String rentid) {
       return null;
    }
}
