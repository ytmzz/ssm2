package com.ytmzz.vo;

import com.ytmzz.pojo.ClassInfo;
import com.ytmzz.pojo.Student;

public class ExaminationVo {
    private Student student;
    private String javaStatus = "未选课";
    private Double javaScore;
    private String mathStatus = "未选课";
    private Double mathScore;
    private String cppStatus = "未选课";
    private Double cppScore;
    // 课程数
    private Integer scoreCount;
    // 课程总分
    private Double scoreSum;
    // 及格课程数
    private Integer passCount;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getJavaStatus() {
        return javaStatus;
    }

    public void setJavaStatus(String javaStatus) {
        this.javaStatus = javaStatus;
    }

    public Double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(Double javaScore) {
        this.javaScore = javaScore;
    }

    public String getMathStatus() {
        return mathStatus;
    }

    public void setMathStatus(String mathStatus) {
        this.mathStatus = mathStatus;
    }

    public Double getMathScore() {
        return mathScore;
    }

    public void setMathScore(Double mathScore) {
        this.mathScore = mathScore;
    }

    public String getCppStatus() {
        return cppStatus;
    }

    public void setCppStatus(String cppStatus) {
        this.cppStatus = cppStatus;
    }

    public Double getCppScore() {
        return cppScore;
    }

    public void setCppScore(Double cppScore) {
        this.cppScore = cppScore;
    }

    public Integer getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(Integer scoreCount) {
        this.scoreCount = scoreCount;
    }

    public Double getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(Double scoreSum) {
        this.scoreSum = scoreSum;
    }

    public Integer getPassCount() {
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    @Override
    public String toString() {
        return "ExaminationVo{" +
                "student=" + student +
                ", javaStatus='" + javaStatus + '\'' +
                ", javaScore=" + javaScore +
                ", mathStatus='" + mathStatus + '\'' +
                ", mathScore=" + mathScore +
                ", cppStatus='" + cppStatus + '\'' +
                ", cppScore=" + cppScore +
                ", scoreCount=" + scoreCount +
                ", scoreSum=" + scoreSum +
                ", passCount=" + passCount +
                '}';
    }
}
