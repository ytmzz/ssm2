package com.ytmzz.service;

import com.ytmzz.pojo.Role;
import com.ytmzz.pojo.User;
import com.ytmzz.condition.UserCondition;
import com.ytmzz.util.PageBean;

import java.util.List;

public interface UserService {
    public List<User> getUserList();

    public List<User> getUserListByCondition(PageBean pageBean, UserCondition userCondition);

    public void changeUserStatus(User user);

    public void setPasswordDefault(User user);

    public User getUser(User user);

    public void updateUser(User user);

    public List<Role> getAssignedRoles(Integer uid);

    public List<Role> getUnAssignedRoles(Integer uid);

    public List<Role> getAllRoles();

    public void assignRole(Integer roleId, Integer userId);

    public void unAssignRole(Integer roleId, Integer userId);

    public void addUser(User user);

    public boolean checkPassword(User user);

    public void changPassword(User user);
}
