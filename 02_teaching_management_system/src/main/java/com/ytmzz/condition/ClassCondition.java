package com.ytmzz.condition;

public class ClassCondition {
    private String conditionClassName;
    private Integer conditionHeadmasterId;

    public String getConditionClassName() {
        return conditionClassName;
    }

    public void setConditionClassName(String conditionClassName) {
        this.conditionClassName = conditionClassName;
    }

    public Integer getConditionHeadmasterId() {
        return conditionHeadmasterId;
    }

    public void setConditionHeadmasterId(Integer conditionHeadmasterId) {
        this.conditionHeadmasterId = conditionHeadmasterId;
    }

    @Override
    public String toString() {
        return "ClassCondition{" +
                "conditionClassName='" + conditionClassName + '\'' +
                ", conditionHeadmasterId=" + conditionHeadmasterId +
                '}';
    }
}
