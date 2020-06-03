package com.ytmzz.service;

import com.ytmzz.pojo.ClassCourseTeacher;
import com.ytmzz.util.PageBean;

import java.util.List;

public interface ClassCourseTeacherService {

    public boolean isStudentTeacher(Integer teacherId, Integer studentId);

    public ClassCourseTeacher selectByClassIdAndTeacherId(Integer classId, Integer teacherId);

    public List<ClassCourseTeacher> selectByTeacherId(PageBean pageBean, Integer userId);
}
