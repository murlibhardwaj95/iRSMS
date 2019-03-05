package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_stParent_data implements Serializable {

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("empId")
    @Expose
    private Integer empId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("sSelect")
    @Expose
    private Integer sSelect;
    private final static long serialVersionUID = 7924232393818075915L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSSelect() {
        return sSelect;
    }

    public void setSSelect(Integer sSelect) {
        this.sSelect = sSelect;
    }
}
