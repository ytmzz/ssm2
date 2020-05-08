package com.ytmzz.service;

import com.ytmzz.pojo.Permission;

import java.util.List;

public interface PermissionService {
    public Permission getPermissionTree();

    public List<Permission> getAllPermission();

    public List<Permission> findByUserId(Integer userId);

    public void savePermission(Permission permission);

    public void deletePermission(Integer permissionId);
}
