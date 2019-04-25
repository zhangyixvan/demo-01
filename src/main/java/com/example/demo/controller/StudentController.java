package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.StudentService;
import com.example.demo.util.BusinessResult;
import com.example.demo.util.JedisUtil;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    JedisUtil jedisUtil;

    /**
     * 获取学生列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/studentList")
    public BusinessResult<List<User>> logout(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        Integer roleId = Integer.parseInt(request.getParameter("roleId"));
        if (!jedisUtil.isLogin(username,roleId)) {
            return new BusinessResult<>(BusinessResult.ResultCode.notLogin,
                    "current user not login or login timeout, please retry login!", Lists.newArrayList());
        }
        if (roleId != User.roleCode.teacher.getCode()&&roleId != User.roleCode.admin.getCode()) {
            return new BusinessResult<>(BusinessResult.ResultCode.notPermission,
                    "current user not have permissions!", Lists.newArrayList());
        }
        return new BusinessResult<>(BusinessResult.ResultCode.success, "", studentService.getStudentList());
    }

}
