package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Admin_TeacherResultDetails_Data implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("tblId")
    @Expose
    private Integer tblId;
    @SerializedName("sClass")
    @Expose
    private String sClass;
    @SerializedName("sSubject")
    @Expose
    private String sSubject;
    @SerializedName("totStud")
    @Expose
    private Integer totStud;
    @SerializedName("dOAPercent")
    @Expose
    private Double dOAPercent;
    @SerializedName("sOAGrade")
    @Expose
    private String sOAGrade;
    private final static long serialVersionUID = -3958341125984432247L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getTblId() {
        return tblId;
    }

    public void setTblId(Integer tblId) {
        this.tblId = tblId;
    }

    public String getSClass() {
        return sClass;
    }

    public void setSClass(String sClass) {
        this.sClass = sClass;
    }

    public String getSSubject() {
        return sSubject;
    }

    public void setSSubject(String sSubject) {
        this.sSubject = sSubject;
    }

    public Integer getTotStud() {
        return totStud;
    }

    public void setTotStud(Integer totStud) {
        this.totStud = totStud;
    }

    public Double getDOAPercent() {
        return dOAPercent;
    }

    public void setDOAPercent(Double dOAPercent) {
        this.dOAPercent = dOAPercent;
    }

    public String getSOAGrade() {
        return sOAGrade;
    }

    public void setSOAGrade(String sOAGrade) {
        this.sOAGrade = sOAGrade;
    }
}
