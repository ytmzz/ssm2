package com.ytmzz.controller;

import com.ytmzz.condition.StudentCondition;
import com.ytmzz.pojo.*;
import com.ytmzz.service.ClassInfoService;
import com.ytmzz.service.ExaminationService;
import com.ytmzz.service.StudentService;
import com.ytmzz.service.ClassCourseTeacherService;
import com.ytmzz.util.PageBean;
import com.ytmzz.util.UploadFile;
import com.ytmzz.vo.ExaminationVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
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

    @RequestMapping("/getCourseList")
    @ResponseBody
    public Map<String, Object> getCourseList(HttpSession session, PageBean pageBean) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = (User) session.getAttribute("loginUser");
            List<ClassCourseTeacher> ccts = cctService.selectByTeacherId(pageBean, user.getUserId());
            map.put("pageBean", pageBean);
            map.put("ccts", ccts);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }

        return map;
    }

    @RequestMapping("/jumpAddExaminationPage")
    public String jumpAddExaminationPage() {
        return "examinationPage/addExamination";
    }

    @RequestMapping("/getClassStudentScoreList")
    @ResponseBody
    public Map<String, Object> getClassStudentScoreList(Integer classId, Integer courseId) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Examination> exams = examinationService.selectByClassIdAndCourseId(classId, courseId);
            map.put("exams", exams);
            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
        }

        return map;
    }

    @RequestMapping("/downloadScoreFile")
    public void downloadScoreFile(Integer classId, Integer courseId,
                                     HttpSession session, HttpServletResponse response) throws IOException {
        // 生成文件
        XSSFWorkbook workBook = examinationService.createScoreFile(classId, courseId);

        String filename = "score.xlsx";
        //String path = session.getServletContext().getRealPath("/download/") + filename;

        //设置文件下载头
        response.setHeader("content-disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
//        response.setContentType("multipart/form-data");
        response.setContentType("application/vnd.ms-excel");
        //BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        // 不要使用ajax跳转到这个链接，否则会抛出管道中断错误
        OutputStream out = response.getOutputStream();
        workBook.write(out);
        out.flush();
        out.close();
    }

    @RequestMapping("/uploadScoreFile")
    @ResponseBody
    public boolean uploadScoreFile(MultipartFile file, HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath("/upload/examination");
        // 文件保存路径
        String path = UploadFile.saveFile(file, realPath);
        boolean flag = examinationService.importExamination(path);
        return flag;
    }
}
