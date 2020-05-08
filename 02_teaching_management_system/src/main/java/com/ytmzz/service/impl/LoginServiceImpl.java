package com.ytmzz.service.impl;

import com.ytmzz.dao.RoleMapper;
import com.ytmzz.dao.UserMapper;
import com.ytmzz.dao.UserRoleMapper;
import com.ytmzz.pojo.Role;
import com.ytmzz.pojo.User;
import com.ytmzz.pojo.UserRole;
import com.ytmzz.service.LoginService;
import com.ytmzz.util.InfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Transactional
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private InfoUtil infoUtil;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User checkUser(User user) {
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
        return userMapper.checkUser(user);
    }

    @Override
    public Role getRole(Role role) {
        return roleMapper.selectByPrimaryKey(role.getRoleId());
    }

    @Override
    public List<Role> getAllRole() {
        return roleMapper.selectAll();
    }

    @Override
    public UserRole checkRole(User user, Role role) {
        //return infoUtil.isExist(user.getUserId(), role.getRoleId());
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(role.getRoleId());
        return userRoleMapper.selectBySelective(userRole);
    }

    @Override
    public boolean checkUserInfo(User user, Role role) {
        return infoUtil.checkUserInfo(user.getUserId(), role.getRoleId());
    }
}
