package com.ytmzz.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class Headmaster {
    private Integer headmasterId;

    private String headmasterName;

    private Integer headmasterAge;

    private Date headmasterHiredate;

    private String headmasterStatus;

    public Integer getHeadmasterId() {
        return headmasterId;
    }

    public void setHeadmasterId(Integer headmasterId) {
        this.headmasterId = headmasterId;
    }

    public String getHeadmasterName() {
        return headmasterName;
    }

    public void setHeadmasterName(String headmasterName) {
        this.headmasterName = headmasterName;
    }

    public Integer getHeadmasterAge() {
        return headmasterAge;
    }

    public void setHeadmasterAge(Integer headmasterAge) {
        this.headmasterAge = headmasterAge;
    }

    public Date getHeadmasterHiredate() {
        return headmasterHiredate;
    }

    public void setHeadmasterHiredate(Date headmasterHiredate) {
        this.headmasterHiredate = headmasterHiredate;
    }

    public String getHeadmasterStatus() {
        return headmasterStatus;
    }

    public void setHeadmasterStatus(String headmasterStatus) {
        this.headmasterStatus = headmasterStatus;
    }

    @Override
    public String toString() {
        return "Headmaster{" +
                "headmasterId=" + headmasterId +
                ", headmasterName='" + headmasterName + '\'' +
                ", headmasterAge=" + headmasterAge +
                ", headmasterHiredate=" + headmasterHiredate +
                ", headmasterStatus='" + headmasterStatus + '\'' +
                '}';
    }
}