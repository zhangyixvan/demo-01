package com.example.demo.mapper;

import com.example.demo.mapper.model.CourseAndStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {

    int choseCourse(List<CourseAndStudent> courseAndStudents);

}
