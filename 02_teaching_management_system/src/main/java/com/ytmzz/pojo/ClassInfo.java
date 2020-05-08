package com.ytmzz.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(value = {"handler"})
public class ClassInfo {
    private Integer classId;

    private String className;

    private Integer headmasterId;

    private Date startDay;

    private Date endDay;

    private Headmaster headmaster;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getHeadmasterId() {
        return headmasterId;
    }

    public void setHeadmasterId(Integer headmasterId) {
        this.headmasterId = headmasterId;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public Headmaster getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(Headmaster headmaster) {
        this.headmaster = headmaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassInfo classInfo = (ClassInfo) o;
        return Objects.equals(classId, classInfo.classId) &&
                Objects.equals(className, classInfo.className) &&
                Objects.equals(headmasterId, classInfo.headmasterId) &&
                Objects.equals(startDay, classInfo.startDay) &&
                Objects.equals(endDay, classInfo.endDay) &&
                Objects.equals(headmaster, classInfo.headmaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, className, headmasterId, startDay, endDay, headmaster);
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", headmasterId=" + headmasterId +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", headmaster=" + headmaster +
                '}';
    }
}