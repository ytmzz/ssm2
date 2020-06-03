package com.ytmzz.service;

import com.ytmzz.condition.TeacherCondition;
import com.ytmzz.pojo.Teacher;
import com.ytmzz.util.PageBean;

import java.util.List;

public interface TeacherService {
    public List<Teacher> getSpareTeachers();

    public List<Teacher> getSupervisorList(PageBean pageBean, TeacherCondition teacherCondition);
}
