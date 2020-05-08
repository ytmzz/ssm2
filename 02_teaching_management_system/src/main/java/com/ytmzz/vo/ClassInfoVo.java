package com.ytmzz.vo;

import com.ytmzz.pojo.ClassInfo;
import com.ytmzz.pojo.Teacher;

public class ClassInfoVo {
    private ClassInfo classInfo;
    private Teacher cppTeacher;
    private Teacher javaTeacher;
    private Teacher mathTeacher;
    private Integer studentCount;

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public Teacher getCppTeacher() {
        return cppTeacher;
    }

    public void setCppTeacher(Teacher cppTeacher) {
        this.cppTeacher = cppTeacher;
    }

    public Teacher getJavaTeacher() {
        return javaTeacher;
    }

    public void setJavaTeacher(Teacher javaTeacher) {
        this.javaTeacher = javaTeacher;
    }

    public Teacher getMathTeacher() {
        return mathTeacher;
    }

    public void setMathTeacher(Teacher mathTeacher) {
        this.mathTeacher = mathTeacher;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return "ClassInfoVo{" +
                "classInfo=" + classInfo +
                ", cppTeacher=" + cppTeacher +
                ", javaTeacher=" + javaTeacher +
                ", mathTeacher=" + mathTeacher +
                ", studentCount=" + studentCount +
                '}';
    }
}
