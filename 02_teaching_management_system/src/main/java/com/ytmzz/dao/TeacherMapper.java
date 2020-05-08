package com.ytmzz.dao;

import com.ytmzz.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer teacherId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    public List<Teacher> selectAll();

    public List<Teacher> selectSelective(Teacher teacher);

    public Integer isExist(Integer teacherId);
}