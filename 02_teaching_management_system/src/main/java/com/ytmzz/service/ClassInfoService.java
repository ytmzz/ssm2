package com.ytmzz.service;

import com.ytmzz.condition.ClassCondition;
import com.ytmzz.dao.ClassInfoMapper;
import com.ytmzz.pojo.ClassInfo;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.ClassInfoVo;
import com.ytmzz.vo.ShiftTransferInfoVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

public interface ClassInfoService {
    public boolean checkClassName(String className);

    public void createNewClass(ClassInfoVo classInfoVo);

    public List<ClassInfoVo> getClassInfoVoByCondition(PageBean pageBean, ClassCondition classCondition);

    public boolean importStudent(String path, Integer classId);

    public ClassInfo getClassInfoByHeadmasterId(Integer headmasterId);

    public ShiftTransferInfoVo getShiftTransferInfo(Integer userId);

    public List<ClassInfo> getAllClassInfo();
}
