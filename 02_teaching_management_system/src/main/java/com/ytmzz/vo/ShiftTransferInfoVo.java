package com.ytmzz.vo;

import com.ytmzz.pojo.ClassInfo;
import com.ytmzz.pojo.Headmaster;
import com.ytmzz.pojo.Student;

import java.util.List;

public class ShiftTransferInfoVo {
    private Student student;
    private Headmaster headmaster;
    private ClassInfo classInfo;

    // 可转班级
    private List<ClassInfo> otherClassInfo;

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

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public List<ClassInfo> getOtherClassInfo() {
        return otherClassInfo;
    }

    public void setOtherClassInfo(List<ClassInfo> otherClassInfo) {
        this.otherClassInfo = otherClassInfo;
    }
}
