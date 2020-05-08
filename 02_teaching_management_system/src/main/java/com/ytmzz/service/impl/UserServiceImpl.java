package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.dao.*;
import com.ytmzz.condition.UserCondition;
import com.ytmzz.pojo.Role;
import com.ytmzz.pojo.UserRole;
import com.ytmzz.util.InfoUtil;
import com.ytmzz.util.PageBean;
import com.ytmzz.pojo.User;
import com.ytmzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private InfoUtil infoUtil;

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

    @Override
    public List<User> getUserList() {
        List<User> users = userMapper.selectAll();

        return users;
    }

    @Override
    public List<User> getUserListByCondition(PageBean pageBean, UserCondition userCondition) {
        pageBean.setCount(userMapper.getCountByCondition(userCondition));
        // 无论pages是多少，最少也显示一页
        // showCount默认5，无需校验
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        List<User> users = userMapper.selectByCondition(userCondition);
        return users;
    }

    @Override
    public void changeUserStatus(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUser(User user) {
        return userMapper.selectByPrimaryKey(user.getUserId());
    }

    @Override
    public void setPasswordDefault(User user) {
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        user.setUserPassword(password);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<Role> getAssignedRoles(Integer userId) {
        User user = userMapper.selectByPrimaryKeyWithOthers(userId);
        List<Role> roles = user.getRoles();
        return roles;
    }

    @Override
    public List<Role> getUnAssignedRoles(Integer userId) {
        List<Role> allRoles = getAllRoles();
        List<Role> assignedRoles = getAssignedRoles(userId);
        allRoles.removeAll(assignedRoles);

        return allRoles;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectAll();
    }

    @Override
    public void assignRole(Integer userId, Integer roleId) {
        UserRole userRole = new UserRole();
        // 判断如果已经在用户信息表不存在对应的信息，则新添加一条
        if( !infoUtil.isExist(userId, roleId) ) {
            userRole.setUserStatus("未完善");
            infoUtil.insertUserInfo(userId, roleId);
        } else {
            // 若以前在用户信息表存在记录，则认为其信息是完整的
            userRole.setUserStatus("已完善");
        }
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

        userRoleMapper.insertSelective(userRole);
    }

    @Override
    public void unAssignRole(Integer userId, Integer roleId) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        userRole = userRoleMapper.selectBySelective(userRole);
        userRoleMapper.deleteByUserIdAndRoleId(userId, roleId);
        // 未完善则清除对应用户信息表信息
        if("未完善".equals(userRole.getUserStatus())) {
            switch (roleId) {
                case 1:
                    administratorMapper.deleteByPrimaryKey(userId);
                    break;
                case 2:
                    studentMapper.deleteByPrimaryKey(userId);
                    break;
                case 3:
                    teacherMapper.deleteByPrimaryKey(userId);
                    break;
                case 4:
                    supervisorMapper.deleteByPrimaryKey(userId);
                    break;
                case 7:
                    headmasterMapper.deleteByPrimaryKey(userId);
                    break;

            }
        }
    }

    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public boolean checkPassword(User user) {
        String userPassword = user.getUserPassword();
        String password = userMapper.selectByPrimaryKey(user.getUserId()).getUserPassword();
        return userPassword.equals(password) ? true : false;
    }

    @Override
    public void changPassword(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
