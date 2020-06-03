package com.ytmzz.dao;

import com.ytmzz.condition.HeadmasterCondition;
import com.ytmzz.pojo.Headmaster;

import java.util.List;

public interface HeadmasterMapper {
    int deleteByPrimaryKey(Integer headmasterId);

    int insert(Headmaster record);

    int insertSelective(Headmaster record);

    Headmaster selectByPrimaryKey(Integer headmasterId);

    int updateByPrimaryKeySelective(Headmaster record);

    int updateByPrimaryKey(Headmaster record);

    public List<Headmaster> selectAll();

    public List<Headmaster> selectSelective(Headmaster headmaster);

    public Integer isExist(Integer headmasterId);

    public Integer getCountByCondition(HeadmasterCondition headmasterCondition);

    public List<Headmaster> selectByCondition(HeadmasterCondition headmasterCondition);
}