package com.ytmzz.service.impl;

import com.ytmzz.dao.HeadmasterMapper;
import com.ytmzz.pojo.Headmaster;
import com.ytmzz.service.HeadmasterService;
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
}
