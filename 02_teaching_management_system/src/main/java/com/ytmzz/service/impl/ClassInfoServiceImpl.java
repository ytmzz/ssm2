package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.condition.ClassCondition;
import com.ytmzz.condition.RoleCondition;
import com.ytmzz.dao.*;
import com.ytmzz.pojo.*;
import com.ytmzz.service.ClassInfoService;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.ClassInfoVo;
import com.ytmzz.vo.ShiftTransferInfoVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;

@Transactional
@Service
public class ClassInfoServiceImpl implements ClassInfoService {
    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private HeadmasterMapper headmasterMapper;
    @Autowired
    private ClassCourseTeacherMapper cctMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean checkClassName(String className) {
        ClassInfo classInfo = classInfoMapper.selectByClassName(className);
        return classInfo == null ? true : false;
    }

    /**
     * 开班
     * 1. 生成班级信息
     * 2. 设置分配老师、班主任状态为已分配
     * 3. 关联教师、课程、班级
     */
    @Override
    public void createNewClass(ClassInfoVo classInfoVo) {
        // 1
        ClassInfo classInfo = classInfoVo.getClassInfo();
        classInfo.setStartDay(new Date());
        classInfoMapper.insertSelective(classInfo);
        // 2, 3
        Headmaster headmaster = new Headmaster();
        headmaster.setHeadmasterId(classInfoVo.getClassInfo().getHeadmasterId());
        headmaster.setHeadmasterStatus("已分配");
        headmasterMapper.updateByPrimaryKeySelective(headmaster);

        Teacher teacher = new Teacher();
        teacher.setTeacherStatus("已分配");
        ClassCourseTeacher cct = new ClassCourseTeacher();
        cct.setClassId(classInfo.getClassId());

        int teacherId = classInfoVo.getCppTeacher().getTeacherId();
        if(teacherId != -1) {
            teacher.setTeacherId(teacherId);
            teacherMapper.updateByPrimaryKeySelective(teacher);
            // 默认cpp的id为3
            cct.setTeacherId(teacherId);
            cct.setCourseId(3);
            cctMapper.insertSelective(cct);
        }
        teacherId = classInfoVo.getJavaTeacher().getTeacherId();
        if(teacherId != -1) {
            teacher.setTeacherId(teacherId);
            teacherMapper.updateByPrimaryKeySelective(teacher);
            // 默认java的id为1
            cct.setTeacherId(teacherId);
            cct.setCourseId(1);
            cctMapper.insertSelective(cct);
        }
        teacherId = classInfoVo.getMathTeacher().getTeacherId();
        if(teacherId != -1) {
            teacher.setTeacherId(teacherId);
            teacherMapper.updateByPrimaryKeySelective(teacher);
            // 默认math的id为2
            cct.setTeacherId(teacherId);
            cct.setCourseId(2);
            cctMapper.insertSelective(cct);
        }
    }

    @Override
    public List<ClassInfoVo> getClassInfoVoByCondition(PageBean pageBean, ClassCondition classCondition) {
        // 设置pageBean
        pageBean.setCount(classInfoMapper.getCountByCondition(classCondition));
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        List<ClassInfo> classInfos = classInfoMapper.selectByCondition(classCondition);
        // 查找其他信息
        List<ClassInfoVo> classInfoVos = new ArrayList<>();

        for (ClassInfo classInfo : classInfos) {
            ClassInfoVo classInfoVo = new ClassInfoVo();
            classInfoVo.setClassInfo(classInfo);
            // 获取teacher
            ClassCourseTeacher cct = new ClassCourseTeacher();
            cct.setClassId(classInfo.getClassId());
            List<ClassCourseTeacher> ccts = cctMapper.selectSelective(cct);

            for (ClassCourseTeacher i : ccts) {
                Integer courseId = i.getCourseId();
                Integer teacherId = i.getTeacherId();

                switch (courseId) {
                    case 1:   // java
                        classInfoVo.setJavaTeacher(teacherMapper.selectByPrimaryKey(teacherId));
                        break;
                    case 2:   // math
                        classInfoVo.setMathTeacher(teacherMapper.selectByPrimaryKey(teacherId));
                        break;
                    case 3:   // c++
                        classInfoVo.setCppTeacher(teacherMapper.selectByPrimaryKey(teacherId));
                        break;
                }
            }
            // 获取班级人数
            int count = studentMapper.selectCountByClassId(classInfo.getClassId());
            classInfoVo.setStudentCount(count);

            classInfoVos.add(classInfoVo);
        }

        return classInfoVos;
    }

    @Override
    public boolean importStudent(String path, Integer classId) {
        File file = new File(path);
        FileInputStream fis = null;
        Workbook wk = null;
        try {
            fis = new FileInputStream(path);
            wk = new XSSFWorkbook(fis);

            // 获取第一个sheet
            Sheet sheet = wk.getSheetAt(0);
            if(!checkFileFormat(sheet)) {
                return false;
            }

            Map<String, Student> map = getStudentList(sheet);

            Iterator<String> iterator = map.keySet().iterator();
            RoleCondition condition = new RoleCondition();
            condition.setConditionRoleName("学生");
            Integer roleId = roleMapper.selectByCondition(condition).get(0).getRoleId();
            while(iterator.hasNext()) {
                User user = new User();
                String userName = iterator.next();
                user.setUserName(userName);
                user.setUserPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
                user.setUserStatus("启用");
                // 添加user获取userId
                userMapper.insertSelective(user);
                // 添加userRole
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(roleId);
                userRoleMapper.insertSelective(userRole);
                // 添加student
                Student student = map.get(userName);
                student.setStudentId(user.getUserId());
                student.setClassId(classId);
                student.setStudentStatus("已分班");
                studentMapper.insertSelective(student);
            }

            fis.close();
        } catch (Exception e) {
            // 加载失败
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private boolean checkFileFormat(Sheet sheet) {
        Row firstRow = sheet.getRow(0); //获取第一行row
        int rowSize = firstRow.getPhysicalNumberOfCells(); //获取row的cell数量
        if(rowSize != 4) {
            return false;
        }
        //默认cell为String格式，如果有专多种格式，需要自己定义switch条件属
        String order = firstRow.getCell(0).getRichStringCellValue().getString();
        String accountName = firstRow.getCell(1).getRichStringCellValue().getString();
        String studentName = firstRow.getCell(2).getRichStringCellValue().getString();
        String age = firstRow.getCell(3).getRichStringCellValue().getString();
        if("序号".equals(order) && "账号名".equals(accountName) && "学生姓名".equals(studentName) && "年龄".equals(age)) {
            return true;
        }
        return false;
    }

    /**
     * 账号名为key
     * 学生信息为value
     */
    private Map<String, Student> getStudentList(Sheet sheet) {
        Map<String, Student> map = new HashMap<>();

        // 获取第一行
        int firstRowNum = sheet.getFirstRowNum();
        // 获取最后一行
        int rowEnd = sheet.getLastRowNum();

        List<Student> list = new ArrayList<>();
        for(int i = firstRowNum + 1; i <= rowEnd; i++) {
            Student stu = new Student();
            // 获取行
            Row row = sheet.getRow(i);
            // 获取账号名
            Cell cell = row.getCell(1);
            String accountName = cell.getStringCellValue();
            // 获取姓名
            cell = row.getCell(2);
            stu.setStudentName( cell.getStringCellValue() );
            // 获取年龄
            cell = row.getCell(3);
            Double d = cell.getNumericCellValue();
            stu.setStudentAge( d.intValue() );
            map.put(accountName, stu);
        }
        return map;
    }

    @Override
    public ClassInfo getClassInfoByHeadmasterId(Integer headmasterId) {
        return classInfoMapper.selectByHeadmasterId(headmasterId);
    }

    @Override
    public ShiftTransferInfoVo getShiftTransferInfo(Integer studentId) {
        ShiftTransferInfoVo infoVo = new ShiftTransferInfoVo();

        Student student = studentMapper.selectByPrimaryKey(studentId);
        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(student.getClassId());
        Headmaster headmaster = headmasterMapper.selectByPrimaryKey(classInfo.getHeadmasterId());
        List<ClassInfo> otherClassInfo = classInfoMapper.selectActiveClass();
        otherClassInfo.remove(classInfo);

        infoVo.setStudent(student);
        infoVo.setClassInfo(classInfo);
        infoVo.setHeadmaster(headmaster);
        infoVo.setOtherClassInfo(otherClassInfo);

        return infoVo;
    }

    @Override
    public List<ClassInfo> getAllClassInfo() {
        return classInfoMapper.selectAllClass();
    }
}
