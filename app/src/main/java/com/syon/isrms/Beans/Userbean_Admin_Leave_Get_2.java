package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_Admin_Leave_Get_2 implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("lLeaveAppId")
    @Expose
    private Integer lLeaveAppId;
    @SerializedName("leaveAppDate")
    @Expose
    private String leaveAppDate;
    @SerializedName("leaveAppNo")
    @Expose
    private Integer leaveAppNo;
    @SerializedName("lEmpId")
    @Expose
    private Integer lEmpId;
    @SerializedName("sEmpName")
    @Expose
    private String sEmpName;
    @SerializedName("sEmpCode")
    @Expose
    private String sEmpCode;
    @SerializedName("leaveFromToDate")
    @Expose
    private String leaveFromToDate;
    @SerializedName("dTotalLeave")
    @Expose
    private Double dTotalLeave;
    @SerializedName("sReason")
    @Expose
    private String sReason;
    @SerializedName("nLeaveAprvStatus")
    @Expose
    private Integer nLeaveAprvStatus;
    @SerializedName("sLeaveAprvStatus")
    @Expose
    private String sLeaveAprvStatus;
    @SerializedName("sAdminRemark")
    @Expose
    private String sAdminRemark;
    @SerializedName("sLeaveDetails")
    @Expose
    private String sLeaveDetails;
    private final static long serialVersionUID = 9008633493108938448L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getLLeaveAppId() {
        return lLeaveAppId;
    }

    public void setLLeaveAppId(Integer lLeaveAppId) {
        this.lLeaveAppId = lLeaveAppId;
    }

    public String getLeaveAppDate() {
        return leaveAppDate;
    }

    public void setLeaveAppDate(String leaveAppDate) {
        this.leaveAppDate = leaveAppDate;
    }

    public Integer getLeaveAppNo() {
        return leaveAppNo;
    }

    public void setLeaveAppNo(Integer leaveAppNo) {
        this.leaveAppNo = leaveAppNo;
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

    public String getSEmpCode() {
        return sEmpCode;
    }

    public void setSEmpCode(String sEmpCode) {
        this.sEmpCode = sEmpCode;
    }

    public String getLeaveFromToDate() {
        return leaveFromToDate;
    }

    public void setLeaveFromToDate(String leaveFromToDate) {
        this.leaveFromToDate = leaveFromToDate;
    }

    public Double getDTotalLeave() {
        return dTotalLeave;
    }

    public void setDTotalLeave(Double dTotalLeave) {
        this.dTotalLeave = dTotalLeave;
    }

    public String getSReason() {
        return sReason;
    }

    public void setSReason(String sReason) {
        this.sReason = sReason;
    }

    public Integer getNLeaveAprvStatus() {
        return nLeaveAprvStatus;
    }

    public void setNLeaveAprvStatus(Integer nLeaveAprvStatus) {
        this.nLeaveAprvStatus = nLeaveAprvStatus;
    }

    public String getSLeaveAprvStatus() {
        return sLeaveAprvStatus;
    }

    public void setSLeaveAprvStatus(String sLeaveAprvStatus) {
        this.sLeaveAprvStatus = sLeaveAprvStatus;
    }

    public String getSAdminRemark() {
        return sAdminRemark;
    }

    public void setSAdminRemark(String sAdminRemark) {
        this.sAdminRemark = sAdminRemark;
    }

    public String getSLeaveDetails() {
        return sLeaveDetails;
    }

    public void setSLeaveDetails(String sLeaveDetails) {
        this.sLeaveDetails = sLeaveDetails;
    }
}

