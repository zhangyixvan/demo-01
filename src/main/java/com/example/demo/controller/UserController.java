package com.example.demo.controller;

import com.example.demo.exception.DemoBusinessException;
import com.example.demo.service.UserService;
import com.example.demo.util.BusinessResult;
import com.example.demo.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JedisUtil jedisUtil;

    @RequestMapping("/login")
    public BusinessResult<String> login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        boolean isSuccess = false;
        try {
            isSuccess = userService.logInIsSuccess(username, password, roleId);
        } catch (DemoBusinessException e) {
            return new BusinessResult<>(BusinessResult.ResultCode.userNotFound,"当前用户不存在!");
        }
        if(isSuccess){
            if(jedisUtil.isLogin(username)){
                return new BusinessResult<>(BusinessResult.ResultCode.isLogon,"current user is Logon status!");
            }
            return new BusinessResult<>(BusinessResult.ResultCode.success,"login success!");
        }else{
            return new BusinessResult<>(BusinessResult.ResultCode.logError,"password error!");
        }
    }

    @RequestMapping("/logout")
    public BusinessResult<String> logout(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        jedisUtil.expire(username,0);
        return new BusinessResult<>(BusinessResult.ResultCode.success,"logout success!");
    }

}
