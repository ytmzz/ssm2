package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.condition.RoleCondition;
import com.ytmzz.dao.RoleMapper;
import com.ytmzz.dao.RolePermissionMapper;
import com.ytmzz.pojo.Role;
import com.ytmzz.service.RoleService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Role> getRoleListByCondition(PageBean pageBean, RoleCondition roleCondition) {
        pageBean.setCount(roleMapper.getCountByCondition(roleCondition));
        // 无论pages是多少，最少也显示一页
        // showCount默认5，无需校验
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        List<Role> roles = roleMapper.selectByCondition(roleCondition);
        return roles;
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void saveAssignPermission(Integer roleId, Integer[] permissionIds) {
        //将角色原有的许可全部删除
        rolePermissionMapper.deleteByRoleId(roleId);
        //再给角色添加新的许可
        Map<String,Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("permissionIds", permissionIds);
        rolePermissionMapper.insertSome(map);
    }

    @Override
    public void addRole(Role role) {
        roleMapper.insertSelective(role);
    }
}
