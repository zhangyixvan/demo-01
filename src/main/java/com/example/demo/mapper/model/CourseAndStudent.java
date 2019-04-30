package com.example.demo.mapper.model;

import lombok.Data;

@Data
public class CourseAndStudent {

    private String id;

    private int courseId;
    /**
     * 学生id
     */
    private int userId;
    /**
     * 课程是否修完，默认为：f
     * 修完，为：t
     */
    private String classIsOver = "f";
    /**
     * 课程分数，默认为 0
     */
    private int grade = 0;

}
