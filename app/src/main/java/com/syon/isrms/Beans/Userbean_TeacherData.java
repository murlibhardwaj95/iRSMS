package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_TeacherData implements Serializable {

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("empId")
    @Expose
    private Integer empId;
    @SerializedName("empName")
    @Expose
    private String empName;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("sSelect")
    @Expose
    private Integer sSelect;
    private final static long serialVersionUID = 3808700773797527778L;

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

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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
