package com.ytmzz.util;

import com.ytmzz.dao.*;
import com.ytmzz.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InfoUtil {
    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private HeadmasterMapper headmasterMapper;
    @Autowired
    private SupervisorMapper supervisorMapper;

    public boolean isExist(Integer userId, Integer roleId) {
        boolean flag = false;
        Integer result;
        switch (roleId) {
            case 1:
                result = administratorMapper.isExist(userId);
                flag = (result == null || result == 0) ? false : true;
                break;
            case 2:
                result = studentMapper.isExist(userId);
                flag = (result == null || result == 0) ? false : true;
                break;
            case 3:
                result = teacherMapper.isExist(userId);
                flag = (result == null || result == 0) ? false : true;
                break;
            case 4:
                result = supervisorMapper.isExist(userId);
                flag = (result == null || result == 0) ? false : true;
                break;
            case 7:
                result = headmasterMapper.isExist(userId);
                flag = (result == null || result == 0) ? false : true;
                break;
        }

        return flag;
    }

    public void insertUserInfo(Integer userId, Integer roleId) {
        switch (roleId) {
            case 1:
                Administrator administrator = new Administrator();
                administrator.setAdministratorId(userId);
                administratorMapper.insertSelective(administrator);
                break;
            case 2:
                Student student = new Student();
                student.setStudentId(userId);
                studentMapper.insertSelective(student);
                break;
            case 3:
                Teacher teacher = new Teacher();
                teacher.setTeacherId(userId);
                teacherMapper.insertSelective(teacher);
                break;
            case 4:
                Supervisor supervisor = new Supervisor();
                supervisor.setSupervisorId(userId);
                supervisorMapper.insertSelective(supervisor);
                break;
            case 7:
                Headmaster headmaster = new Headmaster();
                headmaster.setHeadmasterId(userId);
                headmasterMapper.insertSelective(headmaster);
                break;
        }
    }

    public boolean checkUserInfo(Integer userId, Integer roleId) {
        boolean flag = false;
        Integer result;
        switch (roleId) {
            case 1:
                flag = checkAdministratorInfo(userId);
                break;
            case 2:
                flag = checkStudentInfo(userId);
                break;
            case 3:
                flag = checkTeacherInfo(userId);
                break;
            case 4:
                flag = checkSupervisorInfo(userId);
                break;
            case 7:
                flag = checkHeadmasterInfo(userId);
                break;
        }

        return flag;
    }

    private boolean checkAdministratorInfo(Integer userId) {
        Administrator administrator = administratorMapper.selectByPrimaryKey(userId);
        System.out.println(administrator);
        if(administrator.getAdministratorName() == null) {
            return false;
        }
        return true;
    }

    private boolean checkStudentInfo(Integer userId) {
        Student student = studentMapper.selectByPrimaryKey(userId);
        System.out.println(student);
        if( student.getStudentName() == null ||
            student.getStudentAge() == null) {
            return false;
        }
        return true;
    }

    private boolean checkTeacherInfo(Integer userId) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(userId);
        System.out.println(teacher);
        if( teacher.getTeacherName() == null ||
            teacher.getTeacherAge() == null ||
            teacher.getTeacherHiredate() == null) {
            return false;
        }
        return true;
    }

    private boolean checkSupervisorInfo(Integer userId) {
        Supervisor supervisor = supervisorMapper.selectByPrimaryKey(userId);
        System.out.println(supervisor);
        if(supervisor.getSupervisorName() == null ||
            supervisor.getSupervisorAge() == null ||
            supervisor.getSupervisorHiredate() == null) {
            return false;
        }
        return true;
    }

    private boolean checkHeadmasterInfo(Integer userId) {
        Headmaster headmaster = headmasterMapper.selectByPrimaryKey(userId);
        System.out.println(headmaster);
        if(headmaster.getHeadmasterName() == null ||
            headmaster.getHeadmasterAge() == null ||
            headmaster.getHeadmasterHiredate() == null) {
            return false;
        }
        return true;
    }
}
