package com.ytmzz.controller;

import com.ytmzz.pojo.Administrator;
import com.ytmzz.pojo.Role;
import com.ytmzz.pojo.User;
import com.ytmzz.pojo.UserRole;
import com.ytmzz.service.LoginService;
import com.ytmzz.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LoginService loginService;

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
}
