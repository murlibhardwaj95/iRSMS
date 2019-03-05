
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent_Homework_Date_Data_Bean implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("nDay")
    @Expose
    private Integer nDay;
    @SerializedName("sWeekDay")
    @Expose
    private String sWeekDay;
    @SerializedName("dtHWDate")
    @Expose
    private String dtHWDate;
    @SerializedName("sdtHWDate")
    @Expose
    private String sdtHWDate;
    @SerializedName("sHomeWork")
    @Expose
    private String sHomeWork;
    @SerializedName("lSessionId")
    @Expose
    private Integer lSessionId;
    @SerializedName("sSchCode")
    @Expose
    private String sSchCode;
    private final static long serialVersionUID = -7302410037244201853L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getNDay() {
        return nDay;
    }

    public void setNDay(Integer nDay) {
        this.nDay = nDay;
    }

    public String getSWeekDay() {
        return sWeekDay;
    }

    public void setSWeekDay(String sWeekDay) {
        this.sWeekDay = sWeekDay;
    }

    public String getDtHWDate() {
        return dtHWDate;
    }

    public void setDtHWDate(String dtHWDate) {
        this.dtHWDate = dtHWDate;
    }

    public String getSdtHWDate() {
        return sdtHWDate;
    }

    public void setSdtHWDate(String sdtHWDate) {
        this.sdtHWDate = sdtHWDate;
    }

    public String getSHomeWork() {
        return sHomeWork;
    }

    public void setSHomeWork(String sHomeWork) {
        this.sHomeWork = sHomeWork;
    }

    public Integer getLSessionId() {
        return lSessionId;
    }

    public void setLSessionId(Integer lSessionId) {
        this.lSessionId = lSessionId;
    }

    public String getSSchCode() {
        return sSchCode;
    }

    public void setSSchCode(String sSchCode) {
        this.sSchCode = sSchCode;
    }

}
