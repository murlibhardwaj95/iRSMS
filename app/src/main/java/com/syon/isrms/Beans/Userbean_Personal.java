package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_Personal implements Serializable
{

    @SerializedName("lEPDId")
    @Expose
    private Integer lEPDId;
    @SerializedName("lEmpId")
    @Expose
    private Integer lEmpId;
    @SerializedName("sSchCode")
    @Expose
    private String sSchCode;
    @SerializedName("sEmpCode")
    @Expose
    private String sEmpCode;
    @SerializedName("sEmpName")
    @Expose
    private String sEmpName;
    @SerializedName("sGender")
    @Expose
    private String sGender;
    @SerializedName("dtDOB")
    @Expose
    private String dtDOB;
    @SerializedName("sDOB")
    @Expose
    private String sDOB;
    @SerializedName("sAddress")
    @Expose
    private String sAddress;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("sEmail")
    @Expose
    private String sEmail;
    @SerializedName("sAadharNo")
    @Expose
    private String sAadharNo;
    @SerializedName("sPANNo")
    @Expose
    private String sPANNo;
    @SerializedName("sESINo")
    @Expose
    private String sESINo;
    @SerializedName("sPFNo")
    @Expose
    private String sPFNo;
    @SerializedName("dtJoinDate")
    @Expose
    private String dtJoinDate;
    @SerializedName("sJoinDate")
    @Expose
    private String sJoinDate;
    @SerializedName("sDepartment")
    @Expose
    private String sDepartment;
    @SerializedName("sDesignation")
    @Expose
    private String sDesignation;
    @SerializedName("sPayScale")
    @Expose
    private String sPayScale;
    @SerializedName("mBasic")
    @Expose
    private Double mBasic;
    @SerializedName("empPhoto")
    @Expose
    private String empPhoto;
    private final static long serialVersionUID = -3764188857901174722L;

    public Integer getLEPDId() {
        return lEPDId;
    }

    public void setLEPDId(Integer lEPDId) {
        this.lEPDId = lEPDId;
    }

    public Integer getLEmpId() {
        return lEmpId;
    }

    public void setLEmpId(Integer lEmpId) {
        this.lEmpId = lEmpId;
    }

    public String getSSchCode() {
        return sSchCode;
    }

    public void setSSchCode(String sSchCode) {
        this.sSchCode = sSchCode;
    }

    public String getSEmpCode() {
        return sEmpCode;
    }

    public void setSEmpCode(String sEmpCode) {
        this.sEmpCode = sEmpCode;
    }

    public String getSEmpName() {
        return sEmpName;
    }

    public void setSEmpName(String sEmpName) {
        this.sEmpName = sEmpName;
    }

    public String getSGender() {
        return sGender;
    }

    public void setSGender(String sGender) {
        this.sGender = sGender;
    }

    public String getDtDOB() {
        return dtDOB;
    }

    public void setDtDOB(String dtDOB) {
        this.dtDOB = dtDOB;
    }

    public String getSDOB() {
        return sDOB;
    }

    public void setSDOB(String sDOB) {
        this.sDOB = sDOB;
    }

    public String getSAddress() {
        return sAddress;
    }

    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getSAadharNo() {
        return sAadharNo;
    }

    public void setSAadharNo(String sAadharNo) {
        this.sAadharNo = sAadharNo;
    }

    public String getSPANNo() {
        return sPANNo;
    }

    public void setSPANNo(String sPANNo) {
        this.sPANNo = sPANNo;
    }

    public String getSESINo() {
        return sESINo;
    }

    public void setSESINo(String sESINo) {
        this.sESINo = sESINo;
    }

    public String getSPFNo() {
        return sPFNo;
    }

    public void setSPFNo(String sPFNo) {
        this.sPFNo = sPFNo;
    }

    public String getDtJoinDate() {
        return dtJoinDate;
    }

    public void setDtJoinDate(String dtJoinDate) {
        this.dtJoinDate = dtJoinDate;
    }

    public String getSJoinDate() {
        return sJoinDate;
    }

    public void setSJoinDate(String sJoinDate) {
        this.sJoinDate = sJoinDate;
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

    public String getSPayScale() {
        return sPayScale;
    }

    public void setSPayScale(String sPayScale) {
        this.sPayScale = sPayScale;
    }

    public Double getMBasic() {
        return mBasic;
    }

    public void setMBasic(Double mBasic) {
        this.mBasic = mBasic;
    }

    public String getEmpPhoto() {
        return empPhoto;
    }

    public void setEmpPhoto(String empPhoto) {
        this.empPhoto = empPhoto;
    }

}
