
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Class_Teacher_Result_Fill_Teacher_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sEmpCode")
    @Expose
    private String sEmpCode;
    @SerializedName("sTeacherName")
    @Expose
    private String sTeacherName;
    @SerializedName("sDesignation")
    @Expose
    private String sDesignation;
    @SerializedName("lTeacherId")
    @Expose
    private Integer lTeacherId;
    private final static long serialVersionUID = -449423032136806918L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSEmpCode() {
        return sEmpCode;
    }

    public void setSEmpCode(String sEmpCode) {
        this.sEmpCode = sEmpCode;
    }

    public String getSTeacherName() {
        return sTeacherName;
    }

    public void setSTeacherName(String sTeacherName) {
        this.sTeacherName = sTeacherName;
    }

    public String getSDesignation() {
        return sDesignation;
    }

    public void setSDesignation(String sDesignation) {
        this.sDesignation = sDesignation;
    }

    public Integer getLTeacherId() {
        return lTeacherId;
    }

    public void setLTeacherId(Integer lTeacherId) {
        this.lTeacherId = lTeacherId;
    }

}
