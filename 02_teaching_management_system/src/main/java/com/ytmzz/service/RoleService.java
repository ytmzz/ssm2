package com.ytmzz.service;

import com.ytmzz.condition.RoleCondition;
import com.ytmzz.pojo.Role;
import com.ytmzz.util.PageBean;

import java.util.List;

public interface RoleService {

    public List<Role> getRoleListByCondition(PageBean pageBean, RoleCondition roleCondition);

    public Role getRoleById(Integer roleId);

    public void updateRole(Role role);

    public void saveAssignPermission(Integer rid, Integer[] pids);

    public void addRole(Role role);
}
