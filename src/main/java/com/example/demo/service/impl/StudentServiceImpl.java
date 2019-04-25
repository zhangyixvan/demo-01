package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<User> getStudentList() {
        List<User> studentList = studentMapper.getStudentList();
        return studentList;
    }
}
