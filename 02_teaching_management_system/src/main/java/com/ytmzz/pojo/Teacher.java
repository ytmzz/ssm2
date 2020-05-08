package com.ytmzz.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class Teacher {
    private Integer teacherId;

    private String teacherName;

    private Integer teacherAge;

    private Date teacherHiredate;

    private String teacherStatus;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    public Date getTeacherHiredate() {
        return teacherHiredate;
    }

    public void setTeacherHiredate(Date teacherHiredate) {
        this.teacherHiredate = teacherHiredate;
    }

    public String getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(String teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherAge=" + teacherAge +
                ", teacherHiredate=" + teacherHiredate +
                ", teacherStatus='" + teacherStatus + '\'' +
                '}';
    }
}