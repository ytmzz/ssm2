package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.condition.SupervisorCondition;
import com.ytmzz.dao.SupervisorMapper;
import com.ytmzz.pojo.Student;
import com.ytmzz.pojo.Supervisor;
import com.ytmzz.service.SupervisorService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupervisorServiceImpl implements SupervisorService {
    @Autowired
    private SupervisorMapper supervisorMapper;

    @Override
    public List<Supervisor> getSupervisorList(PageBean pageBean, SupervisorCondition supervisorCondition) {
        pageBean.setCount(supervisorMapper.getCountByCondition(supervisorCondition));
        // 无论pages是多少，最少也显示一页
        // showCount默认5，无需校验
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        List<Supervisor> supervisors = supervisorMapper.selectByCondition(supervisorCondition);
        return supervisors;
    }
}
