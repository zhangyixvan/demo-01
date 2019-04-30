package com.example.demo.controller;

import com.example.demo.bean.Course;
import com.example.demo.bean.User;
import com.example.demo.service.CourseService;
import com.example.demo.util.BusinessResult;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController extends BaseController {

    @Autowired
    CourseService courseService;

    /**
     * 获取课程列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/courseList")
    public BusinessResult<List<Course>> logout(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        Integer roleId = Integer.parseInt(request.getParameter("roleId"));
        if (!jedisUtil.isLogin(username,roleId)) {
            return new BusinessResult<>(BusinessResult.ResultCode.notLogin,
                    "current user not login or login timeout, please retry login!", Lists.newArrayList());
        }
        if(isTeacherAndAdmin(roleId)){
            return new BusinessResult<>(BusinessResult.ResultCode.notPermission,
                    "current user not have permissions!", Lists.newArrayList());
        }

        return new BusinessResult<>(BusinessResult.ResultCode.success, "", courseService.getCourseList());
    }

}
