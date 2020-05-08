package com.ytmzz.pojo;

public class Examination {
    private Integer examinationId;

    private Integer courseId;

    private Integer studentId;

    private Double examinationScore;

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Double getExaminationScore() {
        return examinationScore;
    }

    public void setExaminationScore(Double examinationScore) {
        this.examinationScore = examinationScore;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "examinationId=" + examinationId +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                ", examinationScore=" + examinationScore +
                '}';
    }
}