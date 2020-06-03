package com.ytmzz.service;

import com.ytmzz.condition.StudentCondition;
import com.ytmzz.pojo.Examination;
import com.ytmzz.util.PageBean;
import com.ytmzz.vo.ExaminationVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface ExaminationService {
    public List<ExaminationVo> getExaminationList(PageBean pageBean, StudentCondition studentCondition);

    public Examination selectByStudentIdAndCourseId(Integer studentId, Integer courseId);

    public boolean updateExaminationScore(Examination examination);

    public List<Examination> selectByClassIdAndCourseId(Integer classId, Integer courseId);

    public XSSFWorkbook createScoreFile(Integer classId, Integer courseId);

    public boolean importExamination(String path);
}
