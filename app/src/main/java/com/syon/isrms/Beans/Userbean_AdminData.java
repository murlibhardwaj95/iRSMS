package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_AdminData implements Serializable {

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("empId")
    @Expose
    private Integer empId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("sSelect")
    @Expose
    private Integer sSelect;
    private final static long serialVersionUID = 3007661367344842819L;

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getSSelect() {
        return sSelect;
    }

    public void setSSelect(Integer sSelect) {
        this.sSelect = sSelect;
    }
}
