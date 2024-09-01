package com.hkgroup.sys.service.impl;

import com.hkgroup.sys.domain.User;
import com.hkgroup.sys.domain.UserVo;
import com.hkgroup.sys.mapper.UserMapper;
import com.hkgroup.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(UserVo userVo) {
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }
}
