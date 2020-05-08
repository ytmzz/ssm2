package com.ytmzz.service;

import com.ytmzz.pojo.ClassCourseTeacher;

public interface ClassCourseTeacherService {

    public boolean isStudentTeacher(Integer teacherId, Integer studentId);

    public ClassCourseTeacher selectByClassIdAndTeacherId(Integer classId, Integer teacherId);
}
