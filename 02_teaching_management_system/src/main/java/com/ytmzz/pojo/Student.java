package com.ytmzz.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"})
public class Student {
    private Integer studentId;

    private Integer classId;

    private String studentName;

    private Integer studentAge;

    private String studentStatus;

    private String studentGraduation;

    private Integer studentCompany;

    private ClassInfo classInfo;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getStudentGraduation() {
        return studentGraduation;
    }

    public void setStudentGraduation(String studentGraduation) {
        this.studentGraduation = studentGraduation;
    }

    public Integer getStudentCompany() {
        return studentCompany;
    }

    public void setStudentCompany(Integer studentCompany) {
        this.studentCompany = studentCompany;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", classId=" + classId +
                ", studentName='" + studentName + '\'' +
                ", studentAge=" + studentAge +
                ", studentStatus='" + studentStatus + '\'' +
                ", studentGraduation='" + studentGraduation + '\'' +
                ", studentCompany=" + studentCompany +
                ", classInfo=" + classInfo +
                '}';
    }
}