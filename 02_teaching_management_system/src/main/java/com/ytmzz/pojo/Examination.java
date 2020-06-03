package com.ytmzz.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"})
public class Examination {
    private Integer examinationId;

    private Integer courseId;

    private Integer studentId;

    private Double examinationScore;

    private Course course;

    private Student student;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

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
                ", course=" + course +
                ", student=" + student +
                '}';
    }
}