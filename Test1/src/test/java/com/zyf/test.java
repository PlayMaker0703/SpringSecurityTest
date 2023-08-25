package com.zyf;

import com.zyf.entity.Menu;
import com.zyf.entity.User;
import com.zyf.mapper.MenuMapper;
import com.zyf.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class test {
    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

    @Resource
    PasswordEncoder passwordEncoder;

    @Test
    public void encoder(){
        System.out.println(menuMapper.SelectPermsByUserId((long) 1));
    }


    @Test
    public void test1(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
