package com.ytmzz.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"})
public class ClassCourseTeacher {
    private Integer cctId;

    private Integer courseId;

    private Integer classId;

    private Integer teacherId;

    private Course course;
    private ClassInfo classInfo;
    private Teacher teacher;

    public Integer getCctId() {
        return cctId;
    }

    public void setCctId(Integer cctId) {
        this.cctId = cctId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "ClassCourseTeacher{" +
                "cctId=" + cctId +
                ", courseId=" + courseId +
                ", classId=" + classId +
                ", teacherId=" + teacherId +
                ", course=" + course +
                ", classInfo=" + classInfo +
                ", teacher=" + teacher +
                '}';
    }
}