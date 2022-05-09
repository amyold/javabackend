package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

//定义返回的是json
@RestController
//定义路由
@RequestMapping("/user")
@CrossOrigin(origins = "*")

public class UserController {
    @Resource
    UserMapper userMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));
        if (res==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success();
    }
    @PostMapping("/register")
    public Result<?> resgister(@RequestBody User user) {
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if (res!=null){
            return Result.error("500","用户已存在");
        }
        userMapper.insert(user);
        return Result.success();
    }


}
