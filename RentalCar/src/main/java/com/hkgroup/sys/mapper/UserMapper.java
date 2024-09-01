package com.hkgroup.sys.mapper;

import com.hkgroup.sys.domain.User;
import com.hkgroup.sys.domain.UserVo;

public interface UserMapper {
    User login(UserVo userVo);
}
