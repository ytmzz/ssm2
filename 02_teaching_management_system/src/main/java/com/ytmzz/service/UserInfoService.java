package com.ytmzz.service;

import com.ytmzz.pojo.*;

public interface UserInfoService {

    public boolean updateAdministratorInfo(UserRole userRole, Administrator administrator);

    public Administrator getAdministratorInfo(Integer userId);

    public boolean updateStudentInfo(UserRole userRole, Student student);

    public Student getStudentInfo(Integer userId);

    public Supervisor getSupervisorInfo(Integer userId);

    public Headmaster getHeadmasterInfo(Integer userId);

    public Teacher getTeacherInfo(Integer userId);

    public boolean updateTeacherInfo(UserRole userRole, Teacher teacher);

    public boolean updateSupervisorInfo(UserRole userRole, Supervisor supervisor);

    public boolean updateHeadmasterInfo(UserRole userRole, Headmaster headmaster);
}
