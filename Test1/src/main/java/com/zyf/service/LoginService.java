package com.zyf.service;

import com.zyf.entity.ResponseResult;
import com.zyf.entity.User;

public interface LoginService {
     ResponseResult login(User user);

     ResponseResult logout();
}
