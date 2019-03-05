package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_LeaveData implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sFromDate")
    @Expose
    private String sFromDate;
    @SerializedName("sToDate")
    @Expose
    private String sToDate;
    @SerializedName("sReason")
    @Expose
    private String sReason;
    @SerializedName("sLeaveStatus")
    @Expose
    private String sLeaveStatus;
    @SerializedName("sRemark")
    @Expose
    private String sRemark;
    private final static long serialVersionUID = -1532683988464498683L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSFromDate() {
        return sFromDate;
    }

    public void setSFromDate(String sFromDate) {
        this.sFromDate = sFromDate;
    }

    public String getSToDate() {
        return sToDate;
    }

    public void setSToDate(String sToDate) {
        this.sToDate = sToDate;
    }

    public String getSReason() {
        return sReason;
    }

    public void setSReason(String sReason) {
        this.sReason = sReason;
    }

    public String getSLeaveStatus() {
        return sLeaveStatus;
    }

    public void setSLeaveStatus(String sLeaveStatus) {
        this.sLeaveStatus = sLeaveStatus;
    }

    public String getSRemark() {
        return sRemark;
    }

    public void setSRemark(String sRemark) {
        this.sRemark = sRemark;
    }
}
