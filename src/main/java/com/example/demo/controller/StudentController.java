package com.example.demo.controller;

import com.example.demo.util.BusinessResult;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController extends BaseController {


    /**
     * 获取学生列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/studentList", method = {RequestMethod.POST})
    public BusinessResult logout(HttpServletRequest request) {
        String username = request.getParameter("username");
        Integer roleId = Integer.parseInt(request.getParameter("roleId"));
        if (!isLogin(username, roleId)) {
            return new BusinessResult<>(BusinessResult.ResultCode.notLogin,
                    "current user not login or login timeout, please retry login!", Lists.newArrayList());
        }
        if (isTeacherAndAdmin(roleId)) {
            return new BusinessResult<>(BusinessResult.ResultCode.notPermission,
                    "current user not have permissions!", Lists.newArrayList());
        }
        return new BusinessResult<>(BusinessResult.ResultCode.success, "", studentService.getStudentList());
    }


    /**
     * 学生选课，无老师权限验证
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/choseCourse", method = {RequestMethod.POST})
    public BusinessResult choseCourse(HttpServletRequest request) {
        String username = request.getParameter("username");
        Integer roleId = Integer.parseInt(request.getParameter("roleId"));
        if (!jedisUtil.isLogin(username, roleId)) {
            return new BusinessResult<>(BusinessResult.ResultCode.notLogin,
                    "current user not login or login timeout, please retry login!", Lists.newArrayList());
        }
        List<String> courseLists;
        try {
            courseLists = Lists.newArrayList(request.getParameter("courseList").split(","));
        } catch (Throwable e) {
            return new BusinessResult<>(BusinessResult.ResultCode.system_error,
                    "please check is chose course！", false);
        }
        String studentUserId = request.getParameter("studentUserIds");

        return new BusinessResult<>(BusinessResult.ResultCode.success, "", teacherService.choseCourse(courseLists, studentUserId));
    }

}
