package com.example.demo.controller;

import com.example.demo.util.BusinessResult;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController extends BaseController {




    /**
     * 老师为学生绑定课程
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/choseCourse")
    public BusinessResult choseCourse(HttpServletRequest request, HttpServletResponse response){
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
        List<String> courseLists;
        try{
            courseLists = Lists.newArrayList(request.getParameter("courseList").split(","));
        } catch (Throwable e) {
            return new BusinessResult<>(BusinessResult.ResultCode.system_error,
                    "please check is chose course！", false);
        }
        String studentUserId = request.getParameter("studentUserId");

        return new BusinessResult<>(BusinessResult.ResultCode.success,"",teacherService.choseCourse(courseLists, studentUserId));
    }

}
