package com.example.demo.controller;

import com.example.demo.exception.DemoBusinessException;
import com.example.demo.util.BusinessResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    /**
     * 登入
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public BusinessResult<String> login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        boolean isSuccess;
        try {
            isSuccess = userService.logInIsSuccess(username, password, roleId);
        } catch (DemoBusinessException e) {
            return new BusinessResult<>(BusinessResult.ResultCode.userNotFound,"current user not found!", "");
        }
        if(isSuccess){
            if(isLogin(username, roleId)){
                return new BusinessResult<>(BusinessResult.ResultCode.isLogon,"current user is Logon status!", "");
            }
            return new BusinessResult<>(BusinessResult.ResultCode.success,"login success!", "");
        }else{
            return new BusinessResult<>(BusinessResult.ResultCode.logError,"password error!", "");
        }
    }

    /**
     * 登出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public BusinessResult<String> logout(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        jedisUtil.expire(username,0);
        return new BusinessResult<>(BusinessResult.ResultCode.success,"logout success!", "");
    }

}
