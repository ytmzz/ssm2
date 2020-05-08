package com.ytmzz.controller;

import com.ytmzz.condition.ClassCondition;
import com.ytmzz.pojo.*;
import com.ytmzz.service.ClassInfoService;
import com.ytmzz.service.HeadmasterService;
import com.ytmzz.service.TeacherService;
import com.ytmzz.util.PageBean;
import com.ytmzz.util.UploadFile;
import com.ytmzz.vo.ClassInfoVo;
import com.ytmzz.vo.ShiftTransferInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private HeadmasterService headmasterService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/jumpClassPage")
    public String jumpClassPage() {
        return "classPage/class";
    }

    @RequestMapping("/getClassInfoList")
    @ResponseBody
    public Map<String, Object> getClassInfoList(PageBean pageBean, ClassCondition classCondition) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<ClassInfoVo> classInfoVos = classInfoService.getClassInfoVoByCondition(pageBean, classCondition);
            map.put("pageBean", pageBean);
            map.put("classInfoVos", classInfoVos);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/getAllClassInfo")
    @ResponseBody
    public Map<String, Object> getAllClassInfo() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<ClassInfo> classInfos = classInfoService.getAllClassInfo();
            map.put("classInfos", classInfos);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/jumpNewClassPage")
    public String jumpNewClassPage(HttpSession session) {
        try {
            // 获取未分配的老师与班主任
            List<Teacher> teachers = teacherService.getSpareTeachers();
            List<Headmaster> headmasters = headmasterService.getSpareHeadmasters();
            //if(teachers.size() != 0 && headmasters.size() != 0) {
            session.setAttribute("spareTeachers", teachers);
            session.setAttribute("spareHeadmasters", headmasters);
            return "classPage/newClass";
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取列表失败
        return "main";
    }

    @RequestMapping("/getSpareTeachers")
    @ResponseBody
    public Map<String, Object> getSpareTeachers(HttpSession session) {
        List<Teacher> teachers = (List<Teacher>) session.getAttribute("spareTeachers");
        Map<String, Object> map = new HashMap<>();

        if(teachers != null) {
            map.put("spareTeachers", teachers);
            map.put("result", true);
        } else {
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/getSpareHeadmasters")
    @ResponseBody
    public Map<String, Object> getSpareHeadmasters(HttpSession session) {
        List<Headmaster> headmasters = (List<Headmaster>) session.getAttribute("spareHeadmasters");
        Map<String, Object> map = new HashMap<>();

        if(headmasters != null) {
            map.put("spareHeadmasters", headmasters);
            map.put("result", true);
        } else {
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/checkClassName")
    @ResponseBody
    public boolean checkClassName(String className) {
        boolean flag = false;
        try {
            flag = classInfoService.checkClassName(className);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/createNewClass")
    @ResponseBody
    public boolean createNewClass(ClassInfoVo classInfoVo) {
        boolean flag = false;
        System.out.println(classInfoVo);
        try {
            classInfoService.createNewClass(classInfoVo);
            flag = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/uploadStudentsFile")
    @ResponseBody
    public boolean uploadStudentsFile(MultipartFile file, Integer classId, HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath("/upload/student");
        // 文件保存路径
        String path = UploadFile.saveFile(file, realPath);
        System.out.println(path);
        System.out.println(classId);
        boolean flag = classInfoService.importStudent(path, classId);
        return flag;
    }

    // 转班
    @RequestMapping("/jumpShiftTransferPage")
    public String jumpShiftTransferPage() {
        return "classPage/shiftTransfer";
    }

    @RequestMapping("/getShiftTransferInfo")
    @ResponseBody
    public Map<String, Object> getShiftTransferInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = (User) session.getAttribute("loginUser");
            ShiftTransferInfoVo shiftTransferInfoVo = classInfoService.getShiftTransferInfo(user.getUserId());
            map.put("infoVo", shiftTransferInfoVo);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }

        return map;
    }
}
