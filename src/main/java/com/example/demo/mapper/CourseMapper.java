package com.example.demo.mapper;

import com.example.demo.bean.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Course> getCourseList();

}
