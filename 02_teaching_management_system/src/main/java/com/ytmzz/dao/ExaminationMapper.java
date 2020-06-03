package com.ytmzz.dao;

import com.ytmzz.pojo.Examination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExaminationMapper {
    int deleteByPrimaryKey(Integer examinationId);

    int insert(Examination record);

    int insertSelective(Examination record);

    Examination selectByPrimaryKey(Integer examinationId);

    int updateByPrimaryKeySelective(Examination record);

    int updateByPrimaryKey(Examination record);

    public List<Examination> selectByStudentId(Integer studentId);

    public Examination selectByStudentIdAndCourseId(
            @Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    public List<Examination> selectByClassIdAndCourseId(@Param("classId") Integer classId, @Param("courseId") Integer courseId);
}