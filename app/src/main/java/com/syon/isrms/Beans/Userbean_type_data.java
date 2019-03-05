package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_type_data implements Serializable{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sLeaveType")
    @Expose
    private String sLeaveType;
    @SerializedName("sTotalLeave")
    @Expose
    private String sTotalLeave;
    @SerializedName("sAvailedLeave")
    @Expose
    private String sAvailedLeave;
    @SerializedName("sBalLeave")
    @Expose
    private String sBalLeave;
    private final static long serialVersionUID = 2411502516497796785L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSLeaveType() {
        return sLeaveType;
    }

    public void setSLeaveType(String sLeaveType) {
        this.sLeaveType = sLeaveType;
    }

    public String getSTotalLeave() {
        return sTotalLeave;
    }

    public void setSTotalLeave(String sTotalLeave) {
        this.sTotalLeave = sTotalLeave;
    }

    public String getSAvailedLeave() {
        return sAvailedLeave;
    }

    public void setSAvailedLeave(String sAvailedLeave) {
        this.sAvailedLeave = sAvailedLeave;
    }

    public String getSBalLeave() {
        return sBalLeave;
    }

    public void setSBalLeave(String sBalLeave) {
        this.sBalLeave = sBalLeave;
    }
}
