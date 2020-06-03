package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.condition.TeacherCondition;
import com.ytmzz.dao.TeacherMapper;
import com.ytmzz.pojo.Supervisor;
import com.ytmzz.pojo.Teacher;
import com.ytmzz.service.TeacherService;
import com.ytmzz.util.PageBean;
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

    @Override
    public List<Teacher> getSupervisorList(PageBean pageBean, TeacherCondition teacherCondition) {
        pageBean.setCount(teacherMapper.getCountByCondition(teacherCondition));
        // 无论pages是多少，最少也显示一页
        // showCount默认5，无需校验
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        List<Teacher> teachers = teacherMapper.selectByCondition(teacherCondition);
        return teachers;
    }
}
