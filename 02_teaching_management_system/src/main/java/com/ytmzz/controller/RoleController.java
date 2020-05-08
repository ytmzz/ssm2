package com.ytmzz.controller;

import com.google.protobuf.RpcUtil;
import com.ytmzz.condition.RoleCondition;
import com.ytmzz.pojo.Role;
import com.ytmzz.service.RoleService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/jumpRolePage")
    public String jumpRolePage() {
        return "rolePage/role";
    }

    @RequestMapping("/jumpAddRolePage")
    public String jumpAddRolePage() {
        return "rolePage/addRole";
    }

    @RequestMapping("/jumpEditPage")
    public String jumpEditPage(HttpSession session, Role role) {
        role = roleService.getRoleById(role.getRoleId());

        if(role != null) {
            session.setAttribute("editRole", role);
            return "rolePage/editRole";
        }
        // 获取role失败则跳回原页面
        return "rolePage/role";
    }

    @RequestMapping("/jumpAssignPermissionPage")
    public String jumpAssignPermissionPage(HttpSession session, Role role) {
        role = roleService.getRoleById(role.getRoleId());

        if(role != null) {
            session.setAttribute("editRole", role);
            return "rolePage/assignPermission";
        }
        // 获取role失败则跳回原页面
        return "rolePage/role";
    }

    @RequestMapping("/getRoleList")
    public @ResponseBody
    Map<String, Object> getRoleList(PageBean pageBean, RoleCondition roleCondition) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<Role> roles = roleService.getRoleListByCondition(pageBean, roleCondition);
            // pageBean此时已填充完毕
            map.put("pageBean", pageBean);
            map.put("roles", roles);
            map.put("result", true);
        } catch(Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }

        return map;
    }

    @RequestMapping("/getEditRole")
    @ResponseBody
    public Map<String, Object> getEditRole(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Role role = (Role) session.getAttribute("editRole");
        map.put("editRole", role);

        return map;
    }

    @RequestMapping("/updateRole")
    public @ResponseBody Map<String, Object> updateRole(Role role){
        Map<String, Object> map = new HashMap<>();
        System.out.println(role);
        try{
            roleService.updateRole(role);
            map.put("result", true);
        } catch (Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }

        return map;
    }

    @ResponseBody
    @RequestMapping("saveAssignPermission")
    public Map<String, Object> saveAssignPermission(Integer roleId,Integer[] pids) {
        Map<String, Object> map = new HashMap<>();
        try {
            roleService.saveAssignPermission(roleId,pids);
            map.put("result", true);
        } catch (Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/getRoleId")
    @ResponseBody
    public Integer getRoleId(HttpSession session) {
        Role role = (Role) session.getAttribute("editRole");
        return role.getRoleId();
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public boolean addRole(Role role) {
        boolean flag = false;
        try {
            roleService.addRole(role);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
