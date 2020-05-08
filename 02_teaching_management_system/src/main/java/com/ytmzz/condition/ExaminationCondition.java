package com.ytmzz.condition;

public class ExaminationCondition {
    private String studentName;
    private String className;
    private Integer studentId;
    private Integer headmasterId;
    private Double mixScore;
    private Double maxScore;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Double getMixScore() {
        return mixScore;
    }

    public void setMixScore(Double mixScore) {
        this.mixScore = mixScore;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getHeadmasterId() {
        return headmasterId;
    }

    public void setHeadmasterId(Integer headmasterId) {
        this.headmasterId = headmasterId;
    }

    @Override
    public String toString() {
        return "ExaminationCondition{" +
                "studentName='" + studentName + '\'' +
                ", className='" + className + '\'' +
                ", mixScore=" + mixScore +
                ", maxScore=" + maxScore +
                '}';
    }
}
