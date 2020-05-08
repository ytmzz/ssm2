package com.ytmzz.vo;

import com.ytmzz.pojo.*;

public class UserInfoVo {
    private Administrator administrator;
    private Student student;
    private Headmaster headmaster;
    private Teacher teacher;
    private Supervisor supervisor;

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
}
