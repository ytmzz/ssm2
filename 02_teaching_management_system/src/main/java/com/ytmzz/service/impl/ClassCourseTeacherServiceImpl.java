package com.ytmzz.service.impl;

import com.ytmzz.dao.ClassCourseTeacherMapper;
import com.ytmzz.dao.StudentMapper;
import com.ytmzz.pojo.ClassCourseTeacher;
import com.ytmzz.pojo.Student;
import com.ytmzz.service.ClassCourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassCourseTeacherServiceImpl implements ClassCourseTeacherService {
    @Autowired
    private ClassCourseTeacherMapper cctMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public boolean isStudentTeacher(Integer teacherId, Integer studentId) {
        Student student = studentMapper.selectByPrimaryKey(studentId);
        ClassCourseTeacher classCourseTeacher = new ClassCourseTeacher();
        classCourseTeacher.setClassId(student.getClassId());
        classCourseTeacher.setTeacherId(teacherId);
        List<ClassCourseTeacher> ccts = cctMapper.selectSelective(classCourseTeacher);

        return ccts.size() > 0 ? true : false;
    }

    @Override
    public ClassCourseTeacher selectByClassIdAndTeacherId(Integer classId, Integer teacherId) {
        ClassCourseTeacher cct = new ClassCourseTeacher();
        cct.setClassId(classId);
        cct.setTeacherId(teacherId);

        return cctMapper.selectSelective(cct).get(0);
    }
}
