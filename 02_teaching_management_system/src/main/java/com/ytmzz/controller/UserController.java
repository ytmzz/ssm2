package com.ytmzz.controller;

import com.ytmzz.pojo.Administrator;
import com.ytmzz.pojo.Role;
import com.ytmzz.pojo.User;
import com.ytmzz.service.UserService;
import com.ytmzz.condition.UserCondition;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired

    @RequestMapping("/jumpUserPage")
    public String jumpUserPage() {
        return "userPage/user";
    }

    @RequestMapping("/jumpAddPage")
    public String jumpAddPage() {
        return "userPage/addUser";
    }

    @RequestMapping("/jumpAssignRolePage")
    public String jumpAssignRolePage(User user, HttpSession session) {
        // 存放正在修改的用户
        try {
            user = userService.getUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("editUser", user);
        return "userPage/assignRole";
    }

    @RequestMapping("/jumpEditPage")
    public String jumpEditPage(User user, HttpSession session) {
        // 存放正在修改的用户
        try {
            user = userService.getUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("editUser", user);
        return "userPage/editUser";
    }

    @RequestMapping("/getUserList")
    public @ResponseBody
    Map<String, Object> getUserList(PageBean pageBean, UserCondition userCondition){
        Map<String, Object> map = new HashMap<>();
        try {
            List<User> users = userService.getUserListByCondition(pageBean, userCondition);
            // pageBean此时已填充完毕
            map.put("pageBean", pageBean);
            map.put("users", users);
            map.put("result", true);
        }catch(Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }

        return map;
    }
    @RequestMapping("/changeUserStatus")
    @ResponseBody
    public boolean changeUserStatus(User user) {
        boolean flag = false;
        try {
            userService.changeUserStatus(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @RequestMapping("/setPasswordDefault")
    @ResponseBody
    public boolean setPasswordDefault(User user) {
        boolean flag = false;
        try {
            userService.setPasswordDefault(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @RequestMapping("/getEditUser")
    @ResponseBody
    public User getEditUser(HttpSession session) {
        User user = (User)session.getAttribute("editUser");
        user.setUserPassword("");
        return user;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public boolean updateUser(User user) {
        boolean flag = false;
        try {
            userService.updateUser(user);
            flag = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @RequestMapping("/getAssignRoles")
    @ResponseBody
    public Map<String, Object> getAssignRoles(HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = (User) session.getAttribute("editUser");
            List<Role> assignedRoles = userService.getAssignedRoles(user.getUserId());
            List<Role> unAssignedRoles = userService.getUnAssignedRoles(user.getUserId());

            map.put("assignedRoles", assignedRoles);
            map.put("unAssignedRoles", unAssignedRoles);
            map.put("result", true);
        } catch(Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }

        return map;
    }

    @RequestMapping("/assignRole")
    public @ResponseBody Map<String, Object> assignRole(Role role, HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = (User) session.getAttribute("editUser");
            userService.assignRole(user.getUserId(), role.getRoleId());
            map.put("result", true);
        } catch(Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }

        return map;
    }

    @RequestMapping("/unAssignRole")
    public @ResponseBody Map<String, Object> unAssignRole(Role role, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = (User) session.getAttribute("editUser");
            userService.unAssignRole(user.getUserId(), role.getRoleId());
            map.put("result", true);
        } catch(Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }

        return map;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public boolean addUser(User user) {
        boolean flag = false;

        try {
            user.setUserStatus("启用");
            user.setUserPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            userService.addUser(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    // 以下修改密码
    @RequestMapping("/jumpChangePasswordPage")
    public String jumpChangePasswordPage() {
        return "updateUserInfoPage/changePassword";
    }

    @RequestMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(HttpSession session, String oldPassword) {
        boolean flag = false;
        try {
            User user = (User) session.getAttribute("loginUser");
            user.setUserPassword(DigestUtils.md5DigestAsHex(oldPassword.getBytes()));
            flag = userService.checkPassword(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public boolean updatePassword(HttpSession session, String newPassword) {
        boolean flag = false;
        try {
            User user = (User) session.getAttribute("loginUser");
            user.setUserPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            userService.changPassword(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
