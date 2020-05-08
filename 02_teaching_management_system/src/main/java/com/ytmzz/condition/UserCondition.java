package com.ytmzz.condition;

public class UserCondition {
    private String conditionUserName;
    private String conditionUserStatus;

    public String getConditionUserName() {
        return conditionUserName;
    }

    public void setConditionUserName(String conditionUserName) {
        this.conditionUserName = conditionUserName;
    }

    public String getConditionUserStatus() {
        return conditionUserStatus;
    }

    public void setConditionUserStatus(String conditionUserStatus) {
        this.conditionUserStatus = conditionUserStatus;
    }

    @Override
    public String toString() {
        return "UserCondition{" +
                "conditionUserName='" + conditionUserName + '\'' +
                ", conditionUserStatus='" + conditionUserStatus + '\'' +
                '}';
    }
}
