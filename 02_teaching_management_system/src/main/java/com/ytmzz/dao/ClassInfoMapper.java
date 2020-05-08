package com.ytmzz.dao;

import com.ytmzz.condition.ClassCondition;
import com.ytmzz.pojo.ClassInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassInfoMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    ClassInfo selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);

    public ClassInfo selectByClassName(@Param("className") String className);

    public List<ClassInfo> selectAllByOthers();

    public int getCountByCondition(ClassCondition classCondition);

    public List<ClassInfo> selectByCondition(ClassCondition classCondition);

    // 这里没有考虑一个班主任对应多个班的情况
    public ClassInfo selectByHeadmasterId(Integer headmasterId);

    public List<ClassInfo> selectActiveClass();

    public List<ClassInfo> selectAllClass();
}