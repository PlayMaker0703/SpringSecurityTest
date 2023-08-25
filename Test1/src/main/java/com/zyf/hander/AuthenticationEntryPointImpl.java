package com.zyf.hander;

import com.alibaba.fastjson.JSON;
import com.zyf.entity.ResponseResult;
import com.zyf.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    //认证失败
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //处理异常  认证失败
        ResponseResult responseResult=new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败请查询登录");//枚举类401
        String jsonString = JSON.toJSONString(responseResult);
        WebUtils.renderString(httpServletResponse,jsonString);
    }
}
