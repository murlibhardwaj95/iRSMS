
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent_Event_Monthly_Bean_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sDate")
    @Expose
    private String sDate;
    @SerializedName("nHoliday")
    @Expose
    private Integer nHoliday;
    @SerializedName("sEventName")
    @Expose
    private String sEventName;
    private final static long serialVersionUID = 2440450199745013184L;

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

    public Integer getNHoliday() {
        return nHoliday;
    }

    public void setNHoliday(Integer nHoliday) {
        this.nHoliday = nHoliday;
    }

    public String getSEventName() {
        return sEventName;
    }

    public void setSEventName(String sEventName) {
        this.sEventName = sEventName;
    }

}
