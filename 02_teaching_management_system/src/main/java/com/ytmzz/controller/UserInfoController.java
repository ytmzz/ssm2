package com.ytmzz.controller;

import com.ytmzz.dao.UserRoleMapper;
import com.ytmzz.pojo.*;
import com.ytmzz.service.ClassInfoService;
import com.ytmzz.service.LoginService;
import com.ytmzz.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ClassInfoService classInfoService;

    @RequestMapping("/updateAdministratorInfo")
    @ResponseBody
    public boolean updateAdministratorInfo(HttpSession session, Administrator administrator){
        boolean flag = false;
        try {
            User user = (User) session.getAttribute("loginUser");
            Role role = (Role) session.getAttribute("loginRole");
            UserRole userRole = loginService.checkRole(user, role);
            flag = userInfoService.updateAdministratorInfo(userRole, administrator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/updateTeacherInfo")
    @ResponseBody
    public boolean updateTeacherInfo(HttpSession session, Teacher teacher){
        boolean flag = false;
        try {
            User user = (User) session.getAttribute("loginUser");
            Role role = (Role) session.getAttribute("loginRole");
            UserRole userRole = loginService.checkRole(user, role);
            flag = userInfoService.updateTeacherInfo(userRole, teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/updateStudentInfo")
    @ResponseBody
    public boolean updateStudentInfo(HttpSession session, Student student){
        boolean flag = false;

        try {
            User user = (User) session.getAttribute("loginUser");
            Role role = (Role) session.getAttribute("loginRole");
            UserRole userRole = loginService.checkRole(user, role);
            flag = userInfoService.updateStudentInfo(userRole, student);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/updateSupervisorInfo")
    @ResponseBody
    public boolean updateSupervisorInfo(HttpSession session, Supervisor supervisor){
        boolean flag = false;

        try {
            User user = (User) session.getAttribute("loginUser");
            Role role = (Role) session.getAttribute("loginRole");
            UserRole userRole = loginService.checkRole(user, role);
            flag = userInfoService.updateSupervisorInfo(userRole, supervisor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/updateHeadmasterInfo")
    @ResponseBody
    public boolean updateHeadmasterInfo(HttpSession session, Headmaster headmaster){
        boolean flag = false;

        try {
            User user = (User) session.getAttribute("loginUser");
            Role role = (Role) session.getAttribute("loginRole");
            UserRole userRole = loginService.checkRole(user, role);
            flag = userInfoService.updateHeadmasterInfo(userRole, headmaster);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/getAdministratorInfo")
    @ResponseBody
    public Map<String, Object> getAdministratorInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = (User) session.getAttribute("loginUser");
            Administrator administrator = userInfoService.getAdministratorInfo(user.getUserId());
            map.put("info", administrator);
            map.put("result", true);
        } catch(Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/getHeadmasterInfo")
    @ResponseBody
    public Map<String, Object> getHeadmasterInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = (User) session.getAttribute("loginUser");
            Headmaster headmaster = userInfoService.getHeadmasterInfo(user.getUserId());
            map.put("info", headmaster);
            map.put("result", true);
        } catch(Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }





    @RequestMapping("/getSupervisorInfo")
    @ResponseBody
    public Map<String, Object> getSupervisorInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = (User) session.getAttribute("loginUser");
            Supervisor supervisor = userInfoService.getSupervisorInfo(user.getUserId());
            map.put("info", supervisor);
            map.put("result", true);
        } catch(Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }



    @RequestMapping("/getTeacherInfo")
    @ResponseBody
    public Map<String, Object> getTeacherInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = (User) session.getAttribute("loginUser");
            Teacher teacher = userInfoService.getTeacherInfo(user.getUserId());
            map.put("info", teacher);
            map.put("result", true);
        } catch(Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }



    @RequestMapping("/getStudentInfo")
    @ResponseBody
    public Map<String, Object> getStudentInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = (User) session.getAttribute("loginUser");
            Student student = userInfoService.getStudentInfo(user.getUserId());
//            List<ClassInfo> classInfos = classInfoService.getActiveClassInfo();
//            map.put("classInfos", classInfos);
            map.put("info", student);
            map.put("result", true);
        } catch(Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/jumpUserInfoShowPage")
    public String jumpUserInfoShowPage(HttpSession session) {
        Role role = (Role) session.getAttribute("loginRole");
        switch (role.getRoleId()) {
            case 1:
                return "userInfoShowPage/administratorInfoShow";
            case 2:
                return "userInfoShowPage/studentInfoShow";
            case 3:
                return "userInfoShowPage/teacherInfoShow";
            case 4:
                return "userInfoShowPage/supervisorInfoShow";
            case 7:
                return "userInfoShowPage/headmasterInfoShow";
        }
        return "error";
    }

    @RequestMapping("/JumpUpdateUserInfoPage")
    private String JumpUpdateUserInfoPage(HttpSession session) {
        Role role = (Role) session.getAttribute("loginRole");

        switch (role.getRoleId()) {
            case 1:
                return "updateUserInfoPage/updateAdministratorInfo";
            case 2:
                return "updateUserInfoPage/updateStudentInfo";
            case 3:
                return "updateUserInfoPage/updateTeacherInfo";
            case 4:
                return "updateUserInfoPage/updateSupervisorInfo";
            case 7:
                return "updateUserInfoPage/updateHeadmasterInfo";
        }
        return "redirect:/logout";
    }
}
