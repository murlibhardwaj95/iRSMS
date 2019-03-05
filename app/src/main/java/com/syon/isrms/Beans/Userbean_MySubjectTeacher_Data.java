package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_MySubjectTeacher_Data implements Serializable{

    @SerializedName("srNo")
    @Expose
    private Integer srNo;
    @SerializedName("sSubject")
    @Expose
    private String sSubject;
    @SerializedName("subTeacherId")
    @Expose
    private Integer subTeacherId;
    @SerializedName("subTeacherName")
    @Expose
    private String subTeacherName;
    @SerializedName("subTeacherPhoto")
    @Expose
    private String subTeacherPhoto;
    private final static long serialVersionUID = -8829016410521423252L;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getSSubject() {
        return sSubject;
    }

    public void setSSubject(String sSubject) {
        this.sSubject = sSubject;
    }

    public Integer getSubTeacherId() {
        return subTeacherId;
    }

    public void setSubTeacherId(Integer subTeacherId) {
        this.subTeacherId = subTeacherId;
    }

    public String getSubTeacherName() {
        return subTeacherName;
    }

    public void setSubTeacherName(String subTeacherName) {
        this.subTeacherName = subTeacherName;
    }

    public String getSubTeacherPhoto() {
        return subTeacherPhoto;
    }

    public void setSubTeacherPhoto(String subTeacherPhoto) {
        this.subTeacherPhoto = subTeacherPhoto;
    }
}
