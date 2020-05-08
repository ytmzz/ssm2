package com.ytmzz.controller;

import com.ytmzz.condition.StudentCondition;
import com.ytmzz.pojo.Student;
import com.ytmzz.pojo.User;
import com.ytmzz.service.StudentService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/jumpStudentPage")
    public String jumpStudentPage() {
        return "studentPage/student";
    }

    @RequestMapping("/getStudentList")
    @ResponseBody
    public Map<String, Object> getStudentList(PageBean pageBean, StudentCondition studentCondition) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<Student> students = studentService.getStudentList(pageBean, studentCondition);
            map.put("pageBean", pageBean);
            map.put("students", students);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }

        return map;
    }

    @RequestMapping("/getStudentStatus")
    @ResponseBody
    public Map<String, Object> getStudentStatus(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = (User) session.getAttribute("loginUser");
            Student student = studentService.getStudentById(user.getUserId());
            map.put("studentStatus", student.getStudentStatus());
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }
}
