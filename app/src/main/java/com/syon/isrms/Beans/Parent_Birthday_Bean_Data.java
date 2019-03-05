
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent_Birthday_Bean_Data implements Serializable
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
    @SerializedName("lStudId")
    @Expose
    private Integer lStudId;
    @SerializedName("sStudName")
    @Expose
    private String sStudName;
    @SerializedName("sPhoto")
    @Expose
    private String sPhoto;
    @SerializedName("nDay")
    @Expose
    private Integer nDay;
    @SerializedName("nMonth")
    @Expose
    private Integer nMonth;
    private final static long serialVersionUID = 3573486057475251977L;

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

    public Integer getLStudId() {
        return lStudId;
    }

    public void setLStudId(Integer lStudId) {
        this.lStudId = lStudId;
    }

    public String getSStudName() {
        return sStudName;
    }

    public void setSStudName(String sStudName) {
        this.sStudName = sStudName;
    }

    public String getSPhoto() {
        return sPhoto;
    }

    public void setSPhoto(String sPhoto) {
        this.sPhoto = sPhoto;
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
