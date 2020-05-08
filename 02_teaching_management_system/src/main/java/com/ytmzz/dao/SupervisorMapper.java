package com.ytmzz.dao;

import com.ytmzz.pojo.Supervisor;

public interface SupervisorMapper {
    int deleteByPrimaryKey(Integer supervisorId);

    int insert(Supervisor record);

    int insertSelective(Supervisor record);

    Supervisor selectByPrimaryKey(Integer supervisorId);

    int updateByPrimaryKeySelective(Supervisor record);

    int updateByPrimaryKey(Supervisor record);

    public Integer isExist(Integer supervisorId);
}