package com.ytmzz.dao;

import com.ytmzz.pojo.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer rpId);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer rpId);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    public void deleteByRoleId(Integer roleId);

    public void insertSome(Map<String, Object> map);
}