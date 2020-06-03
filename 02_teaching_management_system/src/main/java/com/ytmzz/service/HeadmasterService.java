package com.ytmzz.service;

import com.ytmzz.condition.HeadmasterCondition;
import com.ytmzz.pojo.Headmaster;
import com.ytmzz.util.PageBean;

import java.util.List;

public interface HeadmasterService {
    public List<Headmaster> getSpareHeadmasters();

    public List<Headmaster> getHeadmasterList(PageBean pageBean, HeadmasterCondition headmasterCondition);
}
