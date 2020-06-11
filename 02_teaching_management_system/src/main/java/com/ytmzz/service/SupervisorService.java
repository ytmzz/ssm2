package com.ytmzz.service;

import com.ytmzz.condition.SupervisorCondition;
import com.ytmzz.pojo.Supervisor;
import com.ytmzz.util.PageBean;

import java.util.List;

public interface SupervisorService {

    public List<Supervisor> getSupervisorList(PageBean pageBean, SupervisorCondition supervisorCondition);
}
