package com.ytmzz.service.impl;

import com.ytmzz.dao.TeacherMapper;
import com.ytmzz.pojo.Teacher;
import com.ytmzz.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getSpareTeachers() {
        Teacher teacher = new Teacher();
        teacher.setTeacherStatus("未分配");
        List<Teacher> teachers = teacherMapper.selectSelective(teacher);
        return teachers;
    }
}
