package com.ytmzz.controller;

import com.ytmzz.pojo.Permission;
import com.ytmzz.pojo.User;
import com.ytmzz.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/jumpPermissionPage")
    public String jumpPermissionPage() {
        return "permissionPage/permission";
    }

    @RequestMapping("/jumpAddPermissionPage")
    public String jumpAddPermissionPage(HttpSession session, Integer parentid) {
        session.setAttribute("parentid", parentid);
        return "permissionPage/addPermission";
    }

    @RequestMapping("/getPermissionTree")
    public @ResponseBody Permission getPermissionTree() {
        //Map<String, Object> map = new HashMap<>();
        Permission root = null;

        root = permissionService.getPermissionTree();

        return root;
    }

    //添加许可
    @ResponseBody
    @RequestMapping("/savePermission")
    public Map<String,Object> savePermission(Permission permission) {
        Map<String,Object> map = new HashMap<>();
        try {
            permissionService.savePermission(permission);
            map.put("result", true);
        }catch(Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }
        return map;
    }
    //删除许可
    @ResponseBody
    @RequestMapping("/deletePermission")
    public Map<String,Object> deletePermission(Integer permissionId) {
        Map<String,Object> map = new HashMap<>();
        try {
            permissionService.deletePermission(permissionId);
            map.put("result", true);
        }catch(Exception e) {
            map.put("result", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/getListByUserId")
    @ResponseBody
    public Map<String,Object> getListByUserId(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute("loginUser");
        //获得所有的许可
        List<Permission> permissionList = permissionService.findByUserId(user.getUserId());
        //声明map用来放置permission
        Map<Integer,Permission> permissionMap = new HashMap<>();
        //先将所有的许可放入map
        for (Permission permission : permissionList) {
            permission.setOpen(true);
            permissionMap.put(permission.getPid(), permission);
        }
        //声明ztree
        List<Permission> ztree = new ArrayList<>();
        //再循环将ztree所需要的json结构组装
        for(Permission permission : permissionList) {
            //判断该许可是否为根节点
            if(permission.getParentid()==0) {
                ztree.add(permission);
            }else {
                //如果不是根节点
                //找出它的父节点
                Permission parent = permissionMap.get(permission.getParentid());
                //并将当前节点添加到父节点的children中
                parent.getChildren().add(permission);
            }
        }
        map.put("permissionList", ztree);
        return map;
    }

    @RequestMapping("/getParentId")
    @ResponseBody
    public Integer getParentId(HttpSession session) {
        Integer parentid = (Integer) session.getAttribute("parentid");

        return parentid;
    }
}
