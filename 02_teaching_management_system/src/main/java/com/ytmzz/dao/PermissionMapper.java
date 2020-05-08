package com.ytmzz.dao;

import com.ytmzz.pojo.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    public List<Permission> selectAll();

    public List<Permission> selectByUserId(Integer userId);

    //public Permission selectByPrimaryKeyWithOthers(Integer permissionId);
}