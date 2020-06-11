package com.ytmzz.controller;

import com.ytmzz.condition.HeadmasterCondition;
import com.ytmzz.condition.SupervisorCondition;
import com.ytmzz.pojo.Headmaster;
import com.ytmzz.pojo.Supervisor;
import com.ytmzz.service.HeadmasterService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/headmaster")
public class HeadmasterController {
    @Autowired
    private HeadmasterService headmasterService;


    @RequestMapping("/jumpHeadmasterPage")
    public String jumpHeadmasterPage() {
        return "personnelPage/headmaster";
    }

    @RequestMapping("/getHeadmasterList")
    @ResponseBody
    public Map<String, Object> getHeadmasterList(PageBean pageBean, HeadmasterCondition headmasterCondition) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<Headmaster> headmasters = headmasterService.getHeadmasterList(pageBean, headmasterCondition);
            map.put("pageBean", pageBean);
            map.put("headmasters", headmasters);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }

        return map;
    }
}
