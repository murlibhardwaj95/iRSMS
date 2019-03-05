package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_parent_studentList implements Serializable{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("studId")
    @Expose
    private Integer studId;
    @SerializedName("admNo")
    @Expose
    private String admNo;
    @SerializedName("studName")
    @Expose
    private String studName;
    @SerializedName("fatherName")
    @Expose
    private String fatherName;
    @SerializedName("sSelect")
    @Expose
    private Integer sSelect;
    private final static long serialVersionUID = 5440680506403054312L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getAdmNo() {
        return admNo;
    }

    public void setAdmNo(String admNo) {
        this.admNo = admNo;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer getSSelect() {
        return sSelect;
    }

    public void setSSelect(Integer sSelect) {
        this.sSelect = sSelect;
    }
}
