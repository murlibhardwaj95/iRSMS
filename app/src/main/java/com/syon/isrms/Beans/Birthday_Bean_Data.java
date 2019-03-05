
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Birthday_Bean_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sDate")
    @Expose
    private String sDate;
    @SerializedName("dtDate")
    @Expose
    private String dtDate;
    @SerializedName("lEmpId")
    @Expose
    private Integer lEmpId;
    @SerializedName("sEmpName")
    @Expose
    private String sEmpName;
    @SerializedName("sPhoto")
    @Expose
    private String sPhoto;
    @SerializedName("sDepartment")
    @Expose
    private String sDepartment;
    @SerializedName("sDesignation")
    @Expose
    private String sDesignation;
    @SerializedName("nDay")
    @Expose
    private Integer nDay;
    @SerializedName("nMonth")
    @Expose
    private Integer nMonth;
    private final static long serialVersionUID = -5084333799319198300L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSDate() {
        return sDate;
    }

    public void setSDate(String sDate) {
        this.sDate = sDate;
    }

    public String getDtDate() {
        return dtDate;
    }

    public void setDtDate(String dtDate) {
        this.dtDate = dtDate;
    }

    public Integer getLEmpId() {
        return lEmpId;
    }

    public void setLEmpId(Integer lEmpId) {
        this.lEmpId = lEmpId;
    }

    public String getSEmpName() {
        return sEmpName;
    }

    public void setSEmpName(String sEmpName) {
        this.sEmpName = sEmpName;
    }

    public String getSPhoto() {
        return sPhoto;
    }

    public void setSPhoto(String sPhoto) {
        this.sPhoto = sPhoto;
    }

    public String getSDepartment() {
        return sDepartment;
    }

    public void setSDepartment(String sDepartment) {
        this.sDepartment = sDepartment;
    }

    public String getSDesignation() {
        return sDesignation;
    }

    public void setSDesignation(String sDesignation) {
        this.sDesignation = sDesignation;
    }

    public Integer getNDay() {
        return nDay;
    }

    public void setNDay(Integer nDay) {
        this.nDay = nDay;
    }

    public Integer getNMonth() {
        return nMonth;
    }

    public void setNMonth(Integer nMonth) {
        this.nMonth = nMonth;
    }

}
