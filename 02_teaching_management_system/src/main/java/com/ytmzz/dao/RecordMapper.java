package com.ytmzz.dao;

import com.ytmzz.pojo.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    public List<Record> findSelective(Record record);

    public int getCountSelective(Record record);

    public List<Record> selectByStudentIdAndClassId(
            @Param("studentId") Integer studentId, @Param("classId") Integer classId);

    public void deleteByRecordIds(Integer[] recordIds);

    public void insertAllRecord(List<Record> records);
}