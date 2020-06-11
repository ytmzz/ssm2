package com.ytmzz.controller;

import com.ytmzz.condition.StudentCondition;
import com.ytmzz.condition.SupervisorCondition;
import com.ytmzz.pojo.Student;
import com.ytmzz.pojo.Supervisor;
import com.ytmzz.service.SupervisorService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {
    @Autowired
    private SupervisorService supervisorService;

    @RequestMapping("/jumpSupervisorPage")
    public String jumpSupervisorPage() {
        return "personnelPage/supervisor";
    }

    @RequestMapping("/getSupervisorList")
    @ResponseBody
    public Map<String, Object> getSupervisorList(PageBean pageBean, SupervisorCondition supervisorCondition) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<Supervisor> supervisors = supervisorService.getSupervisorList(pageBean, supervisorCondition);
            map.put("pageBean", pageBean);
            map.put("supervisors", supervisors);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }

        return map;
    }
}
