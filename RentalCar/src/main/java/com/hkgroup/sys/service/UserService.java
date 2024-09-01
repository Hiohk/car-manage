package com.hkgroup.sys.service;

import com.hkgroup.sys.domain.User;
import com.hkgroup.sys.domain.UserVo;

public interface UserService {
     User login(UserVo userVo);
}
