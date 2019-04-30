package com.example.demo.controller;

import com.example.demo.util.BusinessResult;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("course")
public class CourseController extends BaseController {


    /**
     * 获取课程列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/courseList", method = {RequestMethod.POST})
    public BusinessResult logout(HttpServletRequest request) {
        String username = request.getParameter("username");
        Integer roleId = Integer.parseInt(request.getParameter("roleId"));
        if (!jedisUtil.isLogin(username, roleId)) {
            return new BusinessResult<>(BusinessResult.ResultCode.notLogin,
                    "current user not login or login timeout, please retry login!", Lists.newArrayList());
        }
        if (isTeacherAndAdmin(roleId)) {
            return new BusinessResult<>(BusinessResult.ResultCode.notPermission,
                    "current user not have permissions!", Lists.newArrayList());
        }

        return new BusinessResult<>(BusinessResult.ResultCode.success, "", courseService.getCourseList());
    }

}
