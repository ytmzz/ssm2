package com.ytmzz.condition;

public class RecordCondition {
    private String conditionClassName;
    private String conditionStudentName;
    private Integer conditionHeadmasterId;

    public String getConditionClassName() {
        return conditionClassName;
    }

    public void setConditionClassName(String conditionClassName) {
        this.conditionClassName = conditionClassName;
    }

    public String getConditionStudentName() {
        return conditionStudentName;
    }

    public void setConditionStudentName(String conditionStudentName) {
        this.conditionStudentName = conditionStudentName;
    }

    public Integer getConditionHeadmasterId() {
        return conditionHeadmasterId;
    }

    public void setConditionHeadmasterId(Integer conditionHeadmasterId) {
        this.conditionHeadmasterId = conditionHeadmasterId;
    }

    @Override
    public String toString() {
        return "RecordCondition{" +
                "conditionClassName='" + conditionClassName + '\'' +
                ", conditionStudentName='" + conditionStudentName + '\'' +
                ", conditionHeadmasterId=" + conditionHeadmasterId +
                '}';
    }
}
