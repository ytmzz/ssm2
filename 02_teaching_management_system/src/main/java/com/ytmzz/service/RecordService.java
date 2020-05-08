package com.ytmzz.service;

import com.ytmzz.condition.RecordCondition;
import com.ytmzz.pojo.Record;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.RecordVo;

import java.util.List;

public interface RecordService {
    public List<RecordVo> getRecordVoByCondition(PageBean pageBean, RecordCondition recordCondition);

    public List<Record> getStudentRecords(Integer studentId, Integer classId);

    public void deleteRecord(Integer[] recordIds);

    public void saveRecord(Record record);

    public void saveAllRecord(List<Record> records);
}
