package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    JedisUtil jedisUtil;

    protected boolean isLogin(String username,int roleId){
        return !jedisUtil.isLogin(username,roleId);
    }

    protected boolean isTeacherAndAdmin(int roleId){
        return roleId != User.roleCode.teacher.getCode()&&roleId != User.roleCode.admin.getCode();
    }

}
