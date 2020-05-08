package com.ytmzz.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class ShiftTransfer {
    private Integer stId;

    private Integer stStudentId;

    private Date stStartTime;

    private Integer stHeadmasterId;

    private Date stHeadmasterTime;

    private String stHeadmasterResult;

    private Integer stSupervisorId;

    private Date stSupervisorTime;

    private String stSupervisorResult;

    private Integer preClassId;

    private Integer sufClassId;

    private String stStatus;

    // 关联信息
    private Student student;
    private Headmaster headmaster;
    private Supervisor supervisor;
    private ClassInfo preClassInfo;
    private ClassInfo sufClassInfo;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Headmaster getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(Headmaster headmaster) {
        this.headmaster = headmaster;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public ClassInfo getPreClassInfo() {
        return preClassInfo;
    }

    public void setPreClassInfo(ClassInfo preClassInfo) {
        this.preClassInfo = preClassInfo;
    }

    public ClassInfo getSufClassInfo() {
        return sufClassInfo;
    }

    public void setSufClassInfo(ClassInfo sufClassInfo) {
        this.sufClassInfo = sufClassInfo;
    }

    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    public Integer getStStudentId() {
        return stStudentId;
    }

    public void setStStudentId(Integer stStudentId) {
        this.stStudentId = stStudentId;
    }

    public Date getStStartTime() {
        return stStartTime;
    }

    public void setStStartTime(Date stStartTime) {
        this.stStartTime = stStartTime;
    }

    public Integer getStHeadmasterId() {
        return stHeadmasterId;
    }

    public void setStHeadmasterId(Integer stHeadmasterId) {
        this.stHeadmasterId = stHeadmasterId;
    }

    public Date getStHeadmasterTime() {
        return stHeadmasterTime;
    }

    public void setStHeadmasterTime(Date stHeadmasterTime) {
        this.stHeadmasterTime = stHeadmasterTime;
    }

    public String getStHeadmasterResult() {
        return stHeadmasterResult;
    }

    public void setStHeadmasterResult(String stHeadmasterResult) {
        this.stHeadmasterResult = stHeadmasterResult;
    }

    public Integer getStSupervisorId() {
        return stSupervisorId;
    }

    public void setStSupervisorId(Integer stSupervisorId) {
        this.stSupervisorId = stSupervisorId;
    }

    public Date getStSupervisorTime() {
        return stSupervisorTime;
    }

    public void setStSupervisorTime(Date stSupervisorTime) {
        this.stSupervisorTime = stSupervisorTime;
    }

    public String getStSupervisorResult() {
        return stSupervisorResult;
    }

    public void setStSupervisorResult(String stSupervisorResult) {
        this.stSupervisorResult = stSupervisorResult;
    }

    public Integer getPreClassId() {
        return preClassId;
    }

    public void setPreClassId(Integer preClassId) {
        this.preClassId = preClassId;
    }

    public Integer getSufClassId() {
        return sufClassId;
    }

    public void setSufClassId(Integer sufClassId) {
        this.sufClassId = sufClassId;
    }

    public String getStStatus() {
        return stStatus;
    }

    public void setStStatus(String stStatus) {
        this.stStatus = stStatus;
    }

    @Override
    public String toString() {
        return "ShiftTransfer{" +
                "stId=" + stId +
                ", stStudentId=" + stStudentId +
                ", stStartTime=" + stStartTime +
                ", stHeadmasterId=" + stHeadmasterId +
                ", stHeadmasterTime=" + stHeadmasterTime +
                ", stHeadmasterResult='" + stHeadmasterResult + '\'' +
                ", stSupervisorId=" + stSupervisorId +
                ", stSupervisorTime=" + stSupervisorTime +
                ", stSupervisorResult='" + stSupervisorResult + '\'' +
                ", preClassId=" + preClassId +
                ", sufClassId=" + sufClassId +
                ", stStatus='" + stStatus + '\'' +
                ", student=" + student +
                ", headmaster=" + headmaster +
                ", supervisor=" + supervisor +
                ", preClassInfo=" + preClassInfo +
                ", sufClassInfo=" + sufClassInfo +
                '}';
    }
}