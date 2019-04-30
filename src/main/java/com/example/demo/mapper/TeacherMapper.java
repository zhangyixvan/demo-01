package com.example.demo.mapper;

import com.example.demo.mapper.model.CourseAndStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper {

    int choseCourse(List<CourseAndStudent> courseAndStudents);

    int setGrade(@Param("id") String id, @Param("grade") int grade);
}
