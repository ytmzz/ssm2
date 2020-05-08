package com.ytmzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytmzz.condition.StudentCondition;
import com.ytmzz.dao.ClassCourseTeacherMapper;
import com.ytmzz.dao.ExaminationMapper;
import com.ytmzz.dao.StudentMapper;
import com.ytmzz.pojo.ClassCourseTeacher;
import com.ytmzz.pojo.Examination;
import com.ytmzz.pojo.Student;
import com.ytmzz.service.ExaminationService;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.ExaminationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
}
