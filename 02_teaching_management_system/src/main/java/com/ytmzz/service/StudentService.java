package com.ytmzz.service;

import com.ytmzz.condition.StudentCondition;
import com.ytmzz.pojo.Student;
import com.ytmzz.util.PageBean;

import java.util.List;

public interface StudentService {

    public List<Student> getStudents(Integer classId);

    public List<Student> getStudentList(PageBean pageBean, StudentCondition studentCondition);

    public Student getStudentById(Integer studentId);
}
