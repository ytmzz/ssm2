package com.ytmzz.dao;

import com.ytmzz.pojo.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer urId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer urId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    public void deleteByUserIdAndRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    public UserRole selectBySelective(UserRole userRole);
}