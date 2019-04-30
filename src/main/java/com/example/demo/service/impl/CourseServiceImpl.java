package com.example.demo.service.impl;

import com.example.demo.bean.CourseAndTeacher;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<CourseAndTeacher> getCourseList() {
        return courseMapper.getCourseList();
    }
}
