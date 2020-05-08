package com.ytmzz.dao;

import com.ytmzz.pojo.Role;
import com.ytmzz.pojo.User;
import com.ytmzz.condition.UserCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    public User selectByPrimaryKeyWithOthers(Integer userId);

    public List<User> selectByRoleId(Role roleId);

    public User checkUser(User user);

    public List<User> selectAll();

    public Integer getCountByCondition(UserCondition userCondition);

    public List<User> selectByCondition(UserCondition userCondition);
}