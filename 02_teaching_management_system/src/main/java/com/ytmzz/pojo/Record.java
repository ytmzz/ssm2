package com.ytmzz.pojo;

import java.util.Date;

public class Record {
    private Integer recordId;

    private Integer classId;

    private Integer studentId;

    private Date recordDate;

    private String recordResult;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordResult() {
        return recordResult;
    }

    public void setRecordResult(String recordResult) {
        this.recordResult = recordResult;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordId=" + recordId +
                ", classId=" + classId +
                ", studentId=" + studentId +
                ", recordDate=" + recordDate +
                ", recordResult='" + recordResult + '\'' +
                '}';
    }
}