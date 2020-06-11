package com.ytmzz.controller;

import com.ytmzz.condition.SupervisorCondition;
import com.ytmzz.condition.TeacherCondition;
import com.ytmzz.pojo.Supervisor;
import com.ytmzz.pojo.Teacher;
import com.ytmzz.service.TeacherService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/jumpTeacherPage")
    public String jumpTeacherPage() {
        return "personnelPage/teacher";
    }

    @RequestMapping("/getTeacherList")
    @ResponseBody
    public Map<String, Object> getSupervisorList(PageBean pageBean, TeacherCondition teacherCondition) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<Teacher> teachers = teacherService.getSupervisorList(pageBean, teacherCondition);
            map.put("pageBean", pageBean);
            map.put("teachers", teachers);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }

        return map;
    }
}
