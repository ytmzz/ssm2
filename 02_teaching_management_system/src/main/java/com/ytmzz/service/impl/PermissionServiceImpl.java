package com.ytmzz.service.impl;

import com.ytmzz.dao.PermissionMapper;
import com.ytmzz.pojo.Permission;
import com.ytmzz.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Permission getPermissionTree() {
        return buildPermissionTree(getAllPermission());
    }

    @Override
    public List<Permission> getAllPermission() {
        return permissionMapper.selectAll();
    }

    private Permission buildPermissionTree(List<Permission> permissions) {
        Map<Integer, Permission> map = new HashMap<>();
        Permission root = null;

        for(Permission p : permissions) {
            map.put(p.getPid(), p);
        }
        for(Permission p : permissions) {
            p.setOpen(true);
            if(p.getParentid() == 0) {
                root = map.get(p.getPid());
            } else {
                map.get(p.getParentid()).getChildren().add(p);
            }
        }

        return root;
    }

    @Override
    public List<Permission> findByUserId(Integer userId) {
        return permissionMapper.selectByUserId(userId);
    }

    @Override
    public void savePermission(Permission permission) {
        permissionMapper.insertSelective(permission);
    }

    @Override
    public void deletePermission(Integer permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }
}
