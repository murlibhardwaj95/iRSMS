
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent_Month_Pick_Data implements Serializable
{

    @SerializedName("nStMonth")
    @Expose
    private Integer nStMonth;
    @SerializedName("nStYear")
    @Expose
    private Integer nStYear;
    @SerializedName("nEndMonth")
    @Expose
    private Integer nEndMonth;
    @SerializedName("nEndYear")
    @Expose
    private Integer nEndYear;
    @SerializedName("nSesEndMonth")
    @Expose
    private Integer nSesEndMonth;
    @SerializedName("nSesEndYear")
    @Expose
    private Integer nSesEndYear;
    @SerializedName("lSessionId")
    @Expose
    private Integer lSessionId;
    @SerializedName("sSchCode")
    @Expose
    private String sSchCode;
    private final static long serialVersionUID = -3205746866035984480L;

    public Integer getNStMonth() {
        return nStMonth;
    }

    public void setNStMonth(Integer nStMonth) {
        this.nStMonth = nStMonth;
    }

    public Integer getNStYear() {
        return nStYear;
    }

    public void setNStYear(Integer nStYear) {
        this.nStYear = nStYear;
    }

    public Integer getNEndMonth() {
        return nEndMonth;
    }

    public void setNEndMonth(Integer nEndMonth) {
        this.nEndMonth = nEndMonth;
    }

    public Integer getNEndYear() {
        return nEndYear;
    }

    public void setNEndYear(Integer nEndYear) {
        this.nEndYear = nEndYear;
    }

    public Integer getNSesEndMonth() {
        return nSesEndMonth;
    }

    public void setNSesEndMonth(Integer nSesEndMonth) {
        this.nSesEndMonth = nSesEndMonth;
    }

    public Integer getNSesEndYear() {
        return nSesEndYear;
    }

    public void setNSesEndYear(Integer nSesEndYear) {
        this.nSesEndYear = nSesEndYear;
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
