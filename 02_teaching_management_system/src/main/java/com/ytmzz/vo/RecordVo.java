package com.ytmzz.vo;

import com.ytmzz.pojo.ClassInfo;
import com.ytmzz.pojo.Record;
import com.ytmzz.pojo.Student;

public class RecordVo {
    private ClassInfo classInfo;
    private Student student;
    private Record record;
    private int count;
    private int normalCount;

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(int normalCount) {
        this.normalCount = normalCount;
    }

    @Override
    public String toString() {
        return "RecordVo{" +
                "classInfo=" + classInfo +
                ", student=" + student +
                ", record=" + record +
                ", count=" + count +
                ", normalCount=" + normalCount +
                '}';
    }
}
