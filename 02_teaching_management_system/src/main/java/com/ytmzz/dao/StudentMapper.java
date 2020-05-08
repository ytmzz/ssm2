package com.ytmzz.dao;

import com.ytmzz.condition.StudentCondition;
import com.ytmzz.pojo.Student;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    public int selectCountByClassId(Integer classId);

    public Integer isExist(Integer studentId);

    public List<Student> selectByCondition(StudentCondition studentCondition);

    public int getCountByCondition(StudentCondition studentCondition);

    public List<Student> selectByClassId(Integer classId);
}