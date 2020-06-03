package com.ytmzz.dao;

import com.ytmzz.pojo.ClassCourseTeacher;

import java.util.List;

public interface ClassCourseTeacherMapper {
    int deleteByPrimaryKey(Integer cctId);

    int insert(ClassCourseTeacher record);

    int insertSelective(ClassCourseTeacher record);

    ClassCourseTeacher selectByPrimaryKey(Integer cctId);

    int updateByPrimaryKeySelective(ClassCourseTeacher record);

    int updateByPrimaryKey(ClassCourseTeacher record);

    public List<ClassCourseTeacher> selectSelective(ClassCourseTeacher record);

    public Integer getCountSelective();
}