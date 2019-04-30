package com.example.demo.mapper;

import com.example.demo.bean.CourseAndTeacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<CourseAndTeacher> getCourseList();

}
