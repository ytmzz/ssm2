package com.ytmzz.controller;

import com.ytmzz.pojo.Role;
import com.ytmzz.pojo.ShiftTransfer;
import com.ytmzz.pojo.User;
import com.ytmzz.service.ShiftTransferService;
import com.ytmzz.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shiftTransfer")
public class ShiftTransferController {
    @Autowired
    private ShiftTransferService shiftTransferService;

    @RequestMapping("/checkActiveRecord")
    @ResponseBody
    public boolean checkActiveRecord(HttpSession session) {
        boolean flag = false;
        try {
            User user = (User) session.getAttribute("loginUser");
            flag = shiftTransferService.checkActiveRecord(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/jumpTransferRecord")
    public String jumpTransferRecord() {
        return "shiftTransferPage/transferRecord";
    }

    @RequestMapping("/applyShiftTransfer")
    @ResponseBody
    public boolean applyShiftTransfer(ShiftTransfer shiftTransfer) {
        boolean flag = false;

        try {
            shiftTransfer.setStStatus("申请中");
            shiftTransfer.setStStartTime(new Date());
            flag = shiftTransferService.applyShiftTransfer(shiftTransfer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @RequestMapping("/getShiftTransferList")
    @ResponseBody
    public Map<String, Object> getShiftTransferList(HttpSession session, PageBean pageBean, String stStatus) {
        Map<String, Object> map = new HashMap<>();
        try {
            Role role = (Role) session.getAttribute("loginRole");
            User user = (User) session.getAttribute("loginUser");
            ShiftTransfer shiftTransfer = new ShiftTransfer();
            shiftTransfer.setStStatus(stStatus);
            switch (role.getRoleId()) {
                case 2:
                    shiftTransfer.setStStudentId(user.getUserId());
                    break;
                case 4:
                    // 保持空
                    break;
                case 7:
                    shiftTransfer.setStHeadmasterId(user.getUserId());
                    break;
                default:
                    new Exception("权限错误");
            }
            if("全部".equals(stStatus)) {
                shiftTransfer.setStStatus(null);
            }
            List<ShiftTransfer> shiftTransfers = shiftTransferService.getShiftTransferList(pageBean, shiftTransfer);
            map.put("shiftTransfers", shiftTransfers);
            map.put("currentRole", role);
            map.put("pageBean", pageBean);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/revokeApply")
    @ResponseBody
    public boolean revokeApply(ShiftTransfer shiftTransfer) {
        shiftTransfer.setStStatus("已撤回");
        boolean flag = false;
        try {
            flag = shiftTransferService.revokeApply(shiftTransfer);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @RequestMapping("/operatorHeadmaster")
    @ResponseBody
    public boolean operatorHeadmaster(ShiftTransfer shiftTransfer) {
        shiftTransfer.setStHeadmasterTime(new Date());
        if("驳回".equals(shiftTransfer.getStHeadmasterResult())) {
            shiftTransfer.setStStatus("已驳回");
        }
        boolean flag = false;
        try {
            System.out.println(shiftTransfer);
            flag = shiftTransferService.operatorHeadmaster(shiftTransfer);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @RequestMapping("/operatorSupervisor")
    @ResponseBody
    public boolean operatorSupervisor(HttpSession session, ShiftTransfer shiftTransfer) {
        shiftTransfer.setStSupervisorTime(new Date());
        if("驳回".equals(shiftTransfer.getStSupervisorResult())) {
            shiftTransfer.setStStatus("已驳回");
        } else {
            shiftTransfer.setStStatus("已完成");
        }
        boolean flag = false;
        try {
            User user = (User) session.getAttribute("loginUser");
            shiftTransfer.setStSupervisorId(user.getUserId());
            flag = shiftTransferService.operatorSupervisor(shiftTransfer);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }



}
