package com.ytmzz.pojo;

import java.util.Date;

public class Supervisor {
    private Integer supervisorId;

    private String supervisorName;

    private Integer supervisorAge;

    private Date supervisorHiredate;

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Integer getSupervisorAge() {
        return supervisorAge;
    }

    public void setSupervisorAge(Integer supervisorAge) {
        this.supervisorAge = supervisorAge;
    }

    public Date getSupervisorHiredate() {
        return supervisorHiredate;
    }

    public void setSupervisorHiredate(Date supervisorHiredate) {
        this.supervisorHiredate = supervisorHiredate;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "supervisorId=" + supervisorId +
                ", supervisorName='" + supervisorName + '\'' +
                ", supervisorAge=" + supervisorAge +
                ", supervisorHiredate=" + supervisorHiredate +
                '}';
    }
}