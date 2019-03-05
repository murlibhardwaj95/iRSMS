package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_MyClassTeacher_Data implements Serializable {
    @SerializedName("classTeacherId")
    @Expose
    private Integer classTeacherId;
    @SerializedName("classTeacherName")
    @Expose
    private String classTeacherName;
    @SerializedName("classTeacherPhoto")
    @Expose
    private String classTeacherPhoto;
    private final static long serialVersionUID = 8509342082741323214L;

    public Integer getClassTeacherId() {
        return classTeacherId;
    }

    public void setClassTeacherId(Integer classTeacherId) {
        this.classTeacherId = classTeacherId;
    }

    public String getClassTeacherName() {
        return classTeacherName;
    }

    public void setClassTeacherName(String classTeacherName) {
        this.classTeacherName = classTeacherName;
    }

    public String getClassTeacherPhoto() {
        return classTeacherPhoto;
    }

    public void setClassTeacherPhoto(String classTeacherPhoto) {
        this.classTeacherPhoto = classTeacherPhoto;
    }
}
