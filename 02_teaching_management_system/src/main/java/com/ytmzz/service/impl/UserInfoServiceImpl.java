package com.ytmzz.service.impl;

import com.ytmzz.dao.*;
import com.ytmzz.pojo.*;
import com.ytmzz.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SupervisorMapper supervisorMapper;
    @Autowired
    private HeadmasterMapper headmasterMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public boolean updateAdministratorInfo(UserRole userRole, Administrator administrator) {
        Administrator test = administratorMapper.selectByPrimaryKey(administrator.getAdministratorId());
        boolean administratorFlag = false;
        if(test != null) {
            administratorFlag = administratorMapper.updateByPrimaryKey(administrator) > 0 ? true : false;
        } else {
            administratorFlag = administratorMapper.insert(administrator) > 0 ? true : false;
        }
        userRole.setUserStatus("已完善");
        boolean userRoleFlag = userRoleMapper.updateByPrimaryKeySelective(userRole) > 0 ? true : false;
        return administratorFlag && userRoleFlag;
    }

    @Override
    public boolean updateStudentInfo(UserRole userRole, Student student) {
        Student test = studentMapper.selectByPrimaryKey(student.getStudentId());
        boolean studentFlag = false;
        if(test != null) {
            studentFlag = studentMapper.updateByPrimaryKey(student) > 0 ? true : false;
        } else {
            studentFlag = studentMapper.insert(student) > 0 ? true : false;
        }
        userRole.setUserStatus("已完善");
        boolean userRoleFlag = userRoleMapper.updateByPrimaryKeySelective(userRole) > 0 ? true : false;
        return studentFlag && userRoleFlag;
    }

    @Override
    public boolean updateTeacherInfo(UserRole userRole, Teacher teacher) {
        Teacher test = teacherMapper.selectByPrimaryKey(teacher.getTeacherId());
        boolean teacherFlag = false;
        if(test != null) {
            teacher.setTeacherStatus(test.getTeacherStatus());
            teacherFlag = teacherMapper.updateByPrimaryKey(teacher) > 0 ? true : false;
        } else {
            teacher.setTeacherStatus("未分配");
            teacherFlag = teacherMapper.insert(teacher) > 0 ? true : false;
        }
        userRole.setUserStatus("已完善");
        boolean userRoleFlag = userRoleMapper.updateByPrimaryKeySelective(userRole) > 0 ? true : false;
        return teacherFlag && userRoleFlag;
    }

    @Override
    public boolean updateSupervisorInfo(UserRole userRole, Supervisor supervisor) {
        Supervisor test = supervisorMapper.selectByPrimaryKey(supervisor.getSupervisorId());
        boolean supervisorFlag = false;
        if(test != null) {
            supervisorFlag = supervisorMapper.updateByPrimaryKey(supervisor) > 0 ? true : false;
        } else {
            supervisorFlag = supervisorMapper.insert(supervisor) > 0 ? true : false;
        }
        userRole.setUserStatus("已完善");
        boolean userRoleFlag = userRoleMapper.updateByPrimaryKeySelective(userRole) > 0 ? true : false;
        return supervisorFlag && userRoleFlag;
    }

    @Override
    public boolean updateHeadmasterInfo(UserRole userRole, Headmaster headmaster) {
        Headmaster test = headmasterMapper.selectByPrimaryKey(headmaster.getHeadmasterId());
        boolean headmasterFlag = false;
        if(test != null) {
            headmasterFlag = headmasterMapper.updateByPrimaryKey(headmaster) > 0 ? true : false;
        } else {
            headmaster.setHeadmasterStatus("未分配");
            headmasterFlag = headmasterMapper.insert(headmaster) > 0 ? true : false;
        }
        userRole.setUserStatus("已完善");
        boolean userRoleFlag = userRoleMapper.updateByPrimaryKeySelective(userRole) > 0 ? true : false;
        return headmasterFlag && userRoleFlag;
    }

    @Override
    public Administrator getAdministratorInfo(Integer userId) {
        return administratorMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Student getStudentInfo(Integer userId) {
        return studentMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Supervisor getSupervisorInfo(Integer userId) {
        return supervisorMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Headmaster getHeadmasterInfo(Integer userId) {
        return headmasterMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Teacher getTeacherInfo(Integer userId) {
        return teacherMapper.selectByPrimaryKey(userId);
    }
}
