package com.ytmzz.controller;

import com.ytmzz.pojo.Role;
import com.ytmzz.pojo.User;
import com.ytmzz.pojo.UserRole;
import com.ytmzz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

//    @RequestMapping("/userLogin")
//    public String login() {
//        return "login";
//    }

    @RequestMapping("/jumpMainPage")
    public String jumpMainPage(HttpSession session) {
        return "main";
    }


    // 未完成
    @RequestMapping("/JumpUpdateUserInfoPage")
    private String JumpUpdateUserInfoPage(HttpSession session) {
        Role role = (Role) session.getAttribute("loginRole");
        switch (role.getRoleId()) {
            case 1:
                return "userInfoPage/administratorInfo";
            case 2:
                return "userInfoPage/studentInfo";
            case 3:
                return "userInfoPage/teacherInfo";
            case 4:
                return "userInfoPage/supervisorInfo";
            case 7:
                return "userInfoPage/headmasterInfo";
        }
        return "redirect:/logout";
    }

    @RequestMapping("/checkUser")
    @ResponseBody
    public Map<String, Object> checkUser(HttpSession session, User user, Role role) {
        Map<String, Object> map = new HashMap<>();
        user = loginService.checkUser(user);
        role = loginService.getRole(role);
        if(user != null && role != null) {
            UserRole userRole = loginService.checkRole(user, role);
            if(userRole != null) {
                user.setUserPassword(null);
                session.setAttribute("loginUser", user);
                session.setAttribute("loginRole", role);
                // 用户存在，且属于对应角色时判定成功登陆
                map.put("result", true);
                map.put("status", userRole.getUserStatus());
            } else {
                map.put("result", false);
            }
        } else {
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map<String, Object> getUserInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        // 需要添加返回角色信息
        User user = (User)session.getAttribute("loginUser");
        Role role = (Role)session.getAttribute("loginRole");
        if(user != null && role != null) {
            UserRole userRole = loginService.checkRole(user, role);
            map.put("user", user);
            map.put("role", role);
            map.put("status", userRole.getUserStatus());
            map.put("result", true);
        } else {
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/getAllRole")
    @ResponseBody
    public Map<String, Object> getAllRole() {
        Map<String, Object> map = new HashMap<>();

        try {
            List<Role> roles = loginService.getAllRole();
            map.put("roles", roles);
            map.put("result", true);
        } catch (Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }

        return map;
    }
}
