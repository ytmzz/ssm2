package com.ytmzz.condition;

public class StudentCondition {
    private String conditionStudentName;
    private Integer conditionClassId;

    public String getConditionStudentName() {
        return conditionStudentName;
    }

    public void setConditionStudentName(String conditionStudentName) {
        this.conditionStudentName = conditionStudentName;
    }

    public Integer getConditionClassId() {
        return conditionClassId;
    }

    public void setConditionClassId(Integer conditionClassId) {
        this.conditionClassId = conditionClassId;
    }

    @Override
    public String toString() {
        return "StudentCondition{" +
                "conditionStudentName='" + conditionStudentName + '\'' +
                ", conditionClassId=" + conditionClassId +
                '}';
    }
}
