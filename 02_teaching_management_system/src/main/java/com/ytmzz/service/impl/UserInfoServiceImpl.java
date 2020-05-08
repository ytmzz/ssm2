package com.ytmzz.service.impl;

import com.ytmzz.dao.*;
import com.ytmzz.pojo.Administrator;
import com.ytmzz.pojo.UserRole;
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
    public Administrator getAdministratorInfo(Integer userId) {
        return administratorMapper.selectByPrimaryKey(userId);
    }
}
