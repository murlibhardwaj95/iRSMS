
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent_Notification_Bean_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("dtNotifyDate")
    @Expose
    private String dtNotifyDate;
    @SerializedName("sNotifyDate")
    @Expose
    private String sNotifyDate;
    @SerializedName("sNotifyBody")
    @Expose
    private String sNotifyBody;
    @SerializedName("sNotifyType")
    @Expose
    private String sNotifyType;
    @SerializedName("sNotifyTime")
    @Expose
    private String sNotifyTime;
    private final static long serialVersionUID = -5055216194020051836L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getDtNotifyDate() {
        return dtNotifyDate;
    }

    public void setDtNotifyDate(String dtNotifyDate) {
        this.dtNotifyDate = dtNotifyDate;
    }

    public String getSNotifyDate() {
        return sNotifyDate;
    }

    public void setSNotifyDate(String sNotifyDate) {
        this.sNotifyDate = sNotifyDate;
    }

    public String getSNotifyBody() {
        return sNotifyBody;
    }

    public void setSNotifyBody(String sNotifyBody) {
        this.sNotifyBody = sNotifyBody;
    }

    public String getSNotifyType() {
        return sNotifyType;
    }

    public void setSNotifyType(String sNotifyType) {
        this.sNotifyType = sNotifyType;
    }

    public String getSNotifyTime() {
        return sNotifyTime;
    }

    public void setSNotifyTime(String sNotifyTime) {
        this.sNotifyTime = sNotifyTime;
    }
}
