package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.condition.HeadmasterCondition;
import com.ytmzz.dao.HeadmasterMapper;
import com.ytmzz.pojo.Headmaster;
import com.ytmzz.pojo.Supervisor;
import com.ytmzz.service.HeadmasterService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class HeadmasterServiceImpl implements HeadmasterService {
    @Autowired
    private HeadmasterMapper headmasterMapper;

    @Override
    public List<Headmaster> getSpareHeadmasters() {
        Headmaster headmaster = new Headmaster();
        headmaster.setHeadmasterStatus("未分配");
        List<Headmaster> headmasters = headmasterMapper.selectSelective(headmaster);

        return headmasters;
    }

    @Override
    public List<Headmaster> getHeadmasterList(PageBean pageBean, HeadmasterCondition headmasterCondition) {
        pageBean.setCount(headmasterMapper.getCountByCondition(headmasterCondition));
        // 无论pages是多少，最少也显示一页
        // showCount默认5，无需校验
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        List<Headmaster> headmasters = headmasterMapper.selectByCondition(headmasterCondition);
        return headmasters;
    }
}
