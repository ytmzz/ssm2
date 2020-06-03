package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.condition.RoleCondition;
import com.ytmzz.condition.StudentCondition;
import com.ytmzz.dao.ClassCourseTeacherMapper;
import com.ytmzz.dao.ExaminationMapper;
import com.ytmzz.dao.StudentMapper;
import com.ytmzz.pojo.*;
import com.ytmzz.service.ExaminationService;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.ExaminationVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Service
@Transactional
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ExaminationMapper examinationMapper;
    @Autowired
    private ClassCourseTeacherMapper cctMapper;

    @Override
    public List<ExaminationVo> getExaminationList(PageBean pageBean, StudentCondition studentCondition) {
        // 设置pageBean
        pageBean.setCount(studentMapper.getCountByCondition(studentCondition));
        if(pageBean.getCurrentPage() > pageBean.getPages()) {
            pageBean.setCurrentPage(pageBean.getPages());
        }
        if(pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        }
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getShowCount());
        List<Student> students = studentMapper.selectByCondition(studentCondition);

        List<ExaminationVo> examVos = new ArrayList<>();
        for (Student student : students) {
            ExaminationVo examVo = new ExaminationVo();
            examVo.setStudent(student);

            // 查询班级选课数
            ClassCourseTeacher classCourseTeacher = new ClassCourseTeacher();
            classCourseTeacher.setClassId(student.getClassId());
            List<ClassCourseTeacher> ccts = cctMapper.selectSelective(classCourseTeacher);
            for (ClassCourseTeacher cct : ccts) {
                if(cct.getCourseId() == 1) {
                    examVo.setJavaStatus("未考试");
                } else if(cct.getCourseId() == 2) {
                    examVo.setMathStatus("未考试");
                } else if(cct.getCourseId() == 3) {
                    examVo.setCppStatus("未考试");
                }
            }

            List<Examination> exams = examinationMapper.selectByStudentId(student.getStudentId());
            // 及格课程数
            int passCount = 0;
            // 总分
            double scoreSum = 0;
            for (Examination exam : exams) {
                if(exam.getCourseId() == 1) {
                    examVo.setJavaStatus("已考试");
                    examVo.setJavaScore(exam.getExaminationScore());
                } else if(exam.getCourseId() == 2) {
                    examVo.setMathStatus("已考试");
                    examVo.setMathScore(exam.getExaminationScore());
                } else if(exam.getCourseId() == 3) {
                    examVo.setCppStatus("已考试");
                    examVo.setCppScore(exam.getExaminationScore());
                }
                scoreSum += exam.getExaminationScore();
                if(exam.getExaminationScore() >= 60) {
                    passCount++;
                }
            }
            examVo.setScoreCount(exams.size());
            examVo.setScoreSum(scoreSum);
            examVo.setPassCount(passCount);

            examVos.add(examVo);
        }

        return examVos;
    }

    @Override
    public Examination selectByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        return examinationMapper.selectByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public boolean updateExaminationScore(Examination examination) {
        boolean flag = false;
        if(examination.getExaminationId() == null) {
            flag = examinationMapper.insertSelective(examination) > 0 ? true : false;
        } else {
            flag = examinationMapper.updateByPrimaryKeySelective(examination) > 0 ? true : false;
        }
        return flag;
    }

    @Override
    public List<Examination> selectByClassIdAndCourseId(Integer classId, Integer courseId) {
        return examinationMapper.selectByClassIdAndCourseId(classId, courseId);
    }

    @Override
    public XSSFWorkbook createScoreFile(Integer classId, Integer courseId) {
        // 1. 创建一个workbook
        XSSFWorkbook workbook = workbook = new XSSFWorkbook();
        try {
            List<Examination> exams = selectByClassIdAndCourseId(classId, courseId);

            // 2. 添加一个sheet
            XSSFSheet xssfSheet = workbook.createSheet("score");
            // 3. 添加表头第0行
            XSSFRow row = xssfSheet.createRow(0);
            // 4. 创建单元格，设置表头
            XSSFCell xssfCell = row.createCell(0);
            xssfCell.setCellValue("学生id");
            xssfCell = row.createCell(1);
            xssfCell.setCellValue("学生姓名");
            xssfCell = row.createCell(2);
            xssfCell.setCellValue("课程id");
            xssfCell = row.createCell(3);
            xssfCell.setCellValue("学生课程名");
            xssfCell = row.createCell(4);
            xssfCell.setCellValue("学生成绩");

            int rowLength = 0;
            for (Examination exam : exams) {
                rowLength++;
                row = xssfSheet.createRow(rowLength);
                // 学生id
                xssfCell = row.createCell(0);
                xssfCell.setCellValue(exam.getStudentId());

                // 学生姓名
                xssfCell = row.createCell(1);
                xssfCell.setCellValue(exam.getStudent().getStudentName());
                // 课程id
                xssfCell = row.createCell(2);
                if(exam.getCourseId() != null) {
                    xssfCell.setCellValue(exam.getCourseId());
                } else {
                    xssfCell.setCellValue("");
                }
                // 课程名
                xssfCell = row.createCell(3);
                if(exam.getCourse() != null) {
                    xssfCell.setCellValue(exam.getCourse().getCourseName());
                } else {
                    xssfCell.setCellValue("");
                }
                // 成绩
                xssfCell = row.createCell(4);
                if(exam.getExaminationScore() != null) {
                    xssfCell.setCellValue(exam.getExaminationScore());
                } else {
                    xssfCell.setCellValue("");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }

    @Override
    public boolean importExamination(String path) {
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

            List<Examination> exams = getExaminationList(sheet);

            Iterator<Examination> iterator = exams.iterator();
            while(iterator.hasNext()) {
                Examination exam = iterator.next();
                Examination test = examinationMapper.selectByStudentIdAndCourseId(exam.getStudentId(), exam.getCourseId());
                if(test != null) {
                    exam.setExaminationId(test.getExaminationId());
                    examinationMapper.updateByPrimaryKey(exam);
                } else {
                    examinationMapper.insertSelective(exam);
                }
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
        if(rowSize != 5) {
            return false;
        }
        //默认cell为String格式，如果有专多种格式，需要自己定义switch条件属
        String studentId = firstRow.getCell(0).getRichStringCellValue().getString();
        String studentName = firstRow.getCell(1).getRichStringCellValue().getString();
        String courseId = firstRow.getCell(2).getRichStringCellValue().getString();
        String courseName = firstRow.getCell(3).getRichStringCellValue().getString();
        String courseScore = firstRow.getCell(4).getRichStringCellValue().getString();

        if("学生id".equals(studentId) && "学生姓名".equals(studentName) && "课程id".equals(courseId)
                && "学生课程名".equals(courseName) && "学生成绩".equals(courseScore)) {
            return true;
        }
        return false;
    }

    private List<Examination> getExaminationList(Sheet sheet) {
        List<Examination> list = new ArrayList<>();

        // 获取第一行
        int firstRowNum = sheet.getFirstRowNum();
        // 获取最后一行
        int rowEnd = sheet.getLastRowNum();

        for(int i = firstRowNum + 1; i <= rowEnd; i++) {
            Examination stu = new Examination();
            // 获取行
            Row row = sheet.getRow(i);
            // 获取学生id
            Cell cell = row.getCell(0);
            Double d = cell.getNumericCellValue();
            stu.setStudentId( d.intValue() );
            // 获取课程id
            cell = row.getCell(2);
            d = cell.getNumericCellValue();
            stu.setCourseId( d.intValue() );
            // 获取课程成绩
            cell = row.getCell(4);
            stu.setExaminationScore( cell.getNumericCellValue() );
            list.add(stu);
        }
        return list;
    }
}
