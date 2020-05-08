package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.dao.ShiftTransferMapper;
import com.ytmzz.dao.StudentMapper;
import com.ytmzz.pojo.ShiftTransfer;
import com.ytmzz.pojo.Student;
import com.ytmzz.service.ShiftTransferService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShiftTransferServiceImpl implements ShiftTransferService {
    @Autowired
    private ShiftTransferMapper shiftTransferMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public boolean checkActiveRecord(Integer studentId) {
        return shiftTransferMapper.checkActiveRecord(studentId) > 0 ? true : false;
    }

    // 未判断
    @Override
    public boolean applyShiftTransfer(ShiftTransfer shiftTransfer) {
        return shiftTransferMapper.insertSelective(shiftTransfer) > 0 ? true : false;
    }

    @Override
    public List<ShiftTransfer> getShiftTransferList(PageBean pageBean, ShiftTransfer shiftTransfer) {
        pageBean.setCount(shiftTransferMapper.getCountByCondition(shiftTransfer));
        // 无论pages是多少，最少也显示一页
        // showCount默认5，无需校验
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        return shiftTransferMapper.selectByCondition(shiftTransfer);
    }

    @Override
    public boolean revokeApply(ShiftTransfer shiftTransfer) {
        return shiftTransferMapper.updateByPrimaryKeySelective(shiftTransfer) > 0 ? true : false;
    }

    @Override
    public boolean operatorHeadmaster(ShiftTransfer shiftTransfer) {
        return shiftTransferMapper.updateByPrimaryKeySelective(shiftTransfer) > 0 ? true : false;
    }

    @Override
    public boolean operatorSupervisor(ShiftTransfer shiftTransfer) {
        boolean flag = shiftTransferMapper.updateByPrimaryKeySelective(shiftTransfer) > 0 ? true : false;
        boolean changeStu = false;
        // 修改学生班级
        if(flag) {
            Student student = new Student();
            shiftTransfer = shiftTransferMapper.selectByPrimaryKey(shiftTransfer.getStId());
            student.setStudentId(shiftTransfer.getStStudentId());
            student.setClassId(shiftTransfer.getSufClassId());
            changeStu = studentMapper.updateByPrimaryKeySelective(student) > 0 ? true : false;
        }

        return flag && changeStu;
    }
}
