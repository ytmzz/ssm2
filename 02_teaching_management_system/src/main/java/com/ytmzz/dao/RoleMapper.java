package com.ytmzz.dao;

import com.ytmzz.condition.RoleCondition;
import com.ytmzz.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    public Role selectByPrimaryKeyWithOthers(Integer roleId);

    public List<Role> selectByPermissionId(Integer permissionId);

    public List<Role> selectByUserId(Integer userId);

    public List<Role> selectAll();

    public Integer getCountByCondition(RoleCondition roleCondition);

    public List<Role> selectByCondition(RoleCondition roleCondition);
}