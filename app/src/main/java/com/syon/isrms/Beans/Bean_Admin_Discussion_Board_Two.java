package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bean_Admin_Discussion_Board_Two implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("lDB_IdNo")
    @Expose
    private Integer lDBIdNo;
    @SerializedName("empId")
    @Expose
    private Integer empId;
    @SerializedName("empName")
    @Expose
    private String empName;
    @SerializedName("empCode")
    @Expose
    private String empCode;
    @SerializedName("loginType")
    @Expose
    private Integer loginType;
    @SerializedName("empPhoto")
    @Expose
    private String empPhoto;
    @SerializedName("sMessage")
    @Expose
    private String sMessage;
    @SerializedName("msgDate")
    @Expose
    private String msgDate;
    @SerializedName("msgTime")
    @Expose
    private String msgTime;
    private final static long serialVersionUID = 2878818930915183669L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getLDBIdNo() {
        return lDBIdNo;
    }

    public void setLDBIdNo(Integer lDBIdNo) {
        this.lDBIdNo = lDBIdNo;
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

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getEmpPhoto() {
        return empPhoto;
    }

    public void setEmpPhoto(String empPhoto) {
        this.empPhoto = empPhoto;
    }

    public String getSMessage() {
        return sMessage;
    }

    public void setSMessage(String sMessage) {
        this.sMessage = sMessage;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
}
