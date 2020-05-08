package com.ytmzz.service;

import com.ytmzz.pojo.Role;
import com.ytmzz.pojo.User;
import com.ytmzz.pojo.UserRole;

import java.util.List;

public interface LoginService {
    public User checkUser(User user);

    public List<Role> getAllRole();

    public Role getRole(Role role);

    public UserRole checkRole(User user, Role role);

    public boolean checkUserInfo(User user, Role role);
}
