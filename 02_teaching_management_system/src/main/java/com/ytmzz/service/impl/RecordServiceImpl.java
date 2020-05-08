package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.condition.ClassCondition;
import com.ytmzz.condition.RecordCondition;
import com.ytmzz.condition.StudentCondition;
import com.ytmzz.dao.*;
import com.ytmzz.pojo.ClassInfo;
import com.ytmzz.pojo.Record;
import com.ytmzz.pojo.Student;
import com.ytmzz.service.RecordService;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RecordVo> getRecordVoByCondition(PageBean pageBean, RecordCondition recordCondition) {
        // 设置pageBean
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setConditionStudentName(recordCondition.getConditionStudentName());
        //studentCondition.setConditionClassName(recordCondition.getConditionClassName());
        pageBean.setCount(studentMapper.getCountByCondition(studentCondition));
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        List<Student> students = studentMapper.selectByCondition(studentCondition);
        ClassCondition classCondition = new ClassCondition();
        classCondition.setConditionHeadmasterId(recordCondition.getConditionHeadmasterId());
        classCondition.setConditionClassName(recordCondition.getConditionClassName());
        List<ClassInfo> classInfos = classInfoMapper.selectByCondition(classCondition);

        // 无法查询到转班前的信息
        List<RecordVo> recordVos = new ArrayList<>();
        for (ClassInfo classInfo : classInfos) {
            for (Student student : students) {
                // 获取学生所在班级
                //ClassInfo stuClassInfo = classInfoMapper.selectByPrimaryKey(student.getClassId());
                // 选取学生所在的班级，解决班级的条件查询
                if(student.getClassId() == classInfo.getClassId()) {
                    Record record = new Record();
                    record.setClassId(classInfo.getClassId());
                    record.setStudentId(student.getStudentId());
                    int count = recordMapper.getCountSelective(record);
                    record.setRecordResult("正常");
                    int normalCount = recordMapper.getCountSelective(record);
                    RecordVo recordVo = new RecordVo();
                    recordVo.setClassInfo(classInfo);
                    recordVo.setStudent(student);
                    recordVo.setCount(count);
                    recordVo.setNormalCount(normalCount);
                    recordVos.add(recordVo);
                }
            }
        }

        return recordVos;
    }

    @Override
    public List<Record> getStudentRecords(Integer studentId, Integer classId) {
        return recordMapper.selectByStudentIdAndClassId(studentId, classId);
    }

    @Override
    public void deleteRecord(Integer[] recordIds) {
        recordMapper.deleteByRecordIds(recordIds);
    }

    @Override
    public void saveRecord(Record record) {
        recordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void saveAllRecord(List<Record> records) {
        recordMapper.insertAllRecord(records);
    }
}
