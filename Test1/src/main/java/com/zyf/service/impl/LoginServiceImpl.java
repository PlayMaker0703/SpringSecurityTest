package com.zyf.service.impl;

import com.zyf.entity.LoginUser;
import com.zyf.entity.ResponseResult;
import com.zyf.entity.User;
import com.zyf.service.LoginService;
import com.zyf.utils.JwtUtil;
import com.zyf.utils.RedisCache;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    RedisCache redisCache;

    @Resource
    AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager的authenticate方法来进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //认证没通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        //认证通过 使用userid生成jwt 存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把完整的用户信息存入redis k userid v token
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        redisCache.setCacheObject("login:" + userId, loginUser);
        return new ResponseResult(HttpStatus.OK.value(), "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        // 删除redis中的用户信息 key：
        redisCache.deleteObject("login:"+userId);
        return new ResponseResult(HttpStatus.OK.value(),"注销成功");
    }
}
