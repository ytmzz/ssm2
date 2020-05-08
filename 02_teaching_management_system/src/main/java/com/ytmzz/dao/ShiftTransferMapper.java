package com.ytmzz.dao;

import com.ytmzz.pojo.ShiftTransfer;

import java.util.List;

public interface ShiftTransferMapper {
    int deleteByPrimaryKey(Integer stId);

    int insert(ShiftTransfer record);

    int insertSelective(ShiftTransfer record);

    ShiftTransfer selectByPrimaryKey(Integer stId);

    int updateByPrimaryKeySelective(ShiftTransfer record);

    int updateByPrimaryKey(ShiftTransfer record);

    public int checkActiveRecord(Integer studentId);

    public List<ShiftTransfer> selectByCondition(ShiftTransfer record);

    public int getCountByCondition(ShiftTransfer record);
}