package com.ytmzz.dao;

import com.ytmzz.condition.SupervisorCondition;
import com.ytmzz.pojo.Supervisor;

import java.util.List;

public interface SupervisorMapper {
    int deleteByPrimaryKey(Integer supervisorId);

    int insert(Supervisor record);

    int insertSelective(Supervisor record);

    Supervisor selectByPrimaryKey(Integer supervisorId);

    int updateByPrimaryKeySelective(Supervisor record);

    int updateByPrimaryKey(Supervisor record);

    public Integer isExist(Integer supervisorId);

    public Integer getCountByCondition(SupervisorCondition supervisorCondition);

    public List<Supervisor> selectByCondition(SupervisorCondition supervisorCondition);
}