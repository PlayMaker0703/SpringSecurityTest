package com.zyf.controller;

import com.zyf.entity.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('system:dept:list')") //注解
    @PreAuthorize("@ER.hasAuthority('system:dept:list')")   //	 在SPEL表达式中使用 @ER相当于获取容器中bean的名字未ex的对象。然后再调用这个对象的hasAuthority方法
    public String hello(){
        return "hello";
    }

    @RequestMapping("testCors")
    public ResponseResult getTestCors(){
        return new ResponseResult(HttpStatus.OK.value(), "testCors");
    }
}
