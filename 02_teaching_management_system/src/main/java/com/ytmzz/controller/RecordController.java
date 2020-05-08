package com.ytmzz.controller;

import com.ytmzz.condition.RecordCondition;
import com.ytmzz.pojo.ClassInfo;
import com.ytmzz.pojo.Record;
import com.ytmzz.pojo.Student;
import com.ytmzz.pojo.User;
import com.ytmzz.service.ClassInfoService;
import com.ytmzz.service.RecordService;
import com.ytmzz.service.StudentService;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.RecordVo;
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
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassInfoService classInfoService;

    @RequestMapping("/jumpRecordPage")
    public String jumpRecordPage() {
        return "recordPage/record";
    }

    @RequestMapping("/getRecordList")
    @ResponseBody
    public Map<String, Object> getRecordList(PageBean pageBean, RecordCondition recordCondition) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(recordCondition);
        try {
            List<RecordVo> RecordVos = recordService.getRecordVoByCondition(pageBean, recordCondition);
            map.put("pageBean", pageBean);
            map.put("recordVos", RecordVos);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/getStudentRecords")
    @ResponseBody
    public Map<String, Object> getRecordList(Integer studentId, Integer classId) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<Record> records = recordService.getStudentRecords(studentId, classId);
            map.put("records", records);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }

        return map;
    }

    @RequestMapping("/deleteRecords")
    @ResponseBody
    public boolean deleteRecords(Integer[] recordIds) {
        boolean flag = false;
        try {
            recordService.deleteRecord(recordIds);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/saveRecord")
    @ResponseBody
    public boolean saveRecord(Record record) {
        boolean flag = false;
        try {
            recordService.saveRecord(record);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/jumpNewRecordPage")
    public String jumpNewRecordPage() {
        return "recordPage/newRecord";
    }

    @RequestMapping("/getStudents")
    @ResponseBody
    public Map<String, Object> getStudents(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = (User)session.getAttribute("loginUser");
            ClassInfo classInfo = classInfoService.getClassInfoByHeadmasterId(user.getUserId());
            List<Student> students = studentService.getStudents(classInfo.getClassId());
            map.put("students", students);
            map.put("classInfo", classInfo);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/saveAllRecord")
    @ResponseBody
    public boolean saveAllRecord(@RequestBody List<Record> records) {
        boolean flag = false;
        try {
            recordService.saveAllRecord(records);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
