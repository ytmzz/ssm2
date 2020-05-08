package com.ytmzz.controller;

import com.ytmzz.condition.StudentCondition;
import com.ytmzz.pojo.*;
import com.ytmzz.service.ClassInfoService;
import com.ytmzz.service.ExaminationService;
import com.ytmzz.service.StudentService;
import com.ytmzz.service.ClassCourseTeacherService;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.ExaminationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/examination")
public class ExaminationController {
    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private ClassCourseTeacherService cctService;

    @RequestMapping("/jumpExaminationPage")
    public String jumpExaminationPage() {
        return "examinationPage/examination";
    }

    @RequestMapping("/getExaminationList")
    @ResponseBody
    public Map<String, Object> getExaminationList(HttpSession session, PageBean pageBean,
                                                  StudentCondition studentCondition) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(studentCondition);
        try {
            User user = (User) session.getAttribute("loginUser");
            Role role = (Role) session.getAttribute("loginRole");
            if("学生".equals(role.getRoleName())) {
                Student student = studentService.getStudentById(user.getUserId());
                // 未考虑学生重名情况
                studentCondition.setConditionStudentName(student.getStudentName());
            } else if("班主任".equals(role.getRoleName())) {
                ClassInfo classInfo = classInfoService.getClassInfoByHeadmasterId(user.getUserId());
                studentCondition.setConditionClassId(classInfo.getClassId());
            } else if(studentCondition.getConditionClassId() == -1) {
                // 学级主管与教师 设置 classId为null， 查询全部
                studentCondition.setConditionClassId(null);
            }

            List<ExaminationVo> examVos = examinationService.getExaminationList(pageBean, studentCondition);
            map.put("examVos", examVos);
            map.put("pageBean", pageBean);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }

        return map;
    }

    @RequestMapping("/isStudentTeacher")
    @ResponseBody
    public boolean isStudentTeacher(HttpSession session, Integer studentId) {
        boolean flag = false;

        try {
            Role role = (Role) session.getAttribute("loginRole");
            if("教师".equals(role.getRoleName())) {
                User user = (User) session.getAttribute("loginUser");
                flag = cctService.isStudentTeacher(user.getUserId(), studentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/getExaminationInfo")
    @ResponseBody
    public Map<String, Object> getExaminationInfo(HttpSession session, Integer studentId) {
        Map<String, Object> map = new HashMap<>();

        try {
            Student student = studentService.getStudentById(studentId);
            User user = (User) session.getAttribute("loginUser");
            ClassCourseTeacher cct = cctService.selectByClassIdAndTeacherId(student.getClassId(), user.getUserId());
            Examination examination = examinationService.selectByStudentIdAndCourseId(studentId, cct.getCourseId());

            map.put("student", student);
            map.put("cct", cct);
            map.put("examination", examination);
            map.put("result", true);
        } catch(Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        System.out.println(map);

        return map;
    }

    @RequestMapping("/updateExaminationScore")
    @ResponseBody
    public boolean updateExaminationScore(Examination examination) {
        boolean flag = false;

        try {
            System.out.println(examination);
            flag = examinationService.updateExaminationScore(examination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
