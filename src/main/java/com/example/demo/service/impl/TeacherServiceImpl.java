package com.example.demo.service.impl;

import com.example.demo.mapper.TeacherMapper;
import com.example.demo.mapper.model.CourseAndStudent;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public boolean choseCourse(List<String> courseList, String studentUserId) {
        List<CourseAndStudent> courseAndStudents = courseList.stream().map(item -> {
            CourseAndStudent courseAndStudent = new CourseAndStudent();
            courseAndStudent.setId(UUID.randomUUID().toString().replace("-", ""));
            courseAndStudent.setCourseId(Integer.parseInt(item));
            courseAndStudent.setUserId(Integer.parseInt(studentUserId));
            return courseAndStudent;
        }).collect(Collectors.toList());
        int i = teacherMapper.choseCourse(courseAndStudents);
        if (i == courseList.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean setGrade(String id, int grade) {
        int i = teacherMapper.setGrade(id, grade);
        if(i>=0){
            return true;
        } else {
            return false;
        }
    }
}
