package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.condition.StudentCondition;
import com.ytmzz.dao.StudentMapper;
import com.ytmzz.pojo.Student;
import com.ytmzz.pojo.User;
import com.ytmzz.service.StudentService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getStudents(Integer classId) {
        return studentMapper.selectByClassId(classId);
    }

    @Override
    public List<Student> getStudentList(PageBean pageBean, StudentCondition studentCondition) {
        pageBean.setCount(studentMapper.getCountByCondition(studentCondition));
        // 无论pages是多少，最少也显示一页
        // showCount默认5，无需校验
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        List<Student> students = studentMapper.selectByCondition(studentCondition);
        return students;
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }
}
