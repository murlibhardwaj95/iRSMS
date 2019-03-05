
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event_calender_Yearly_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sMonthYear")
    @Expose
    private String sMonthYear;
    @SerializedName("nMonth")
    @Expose
    private Integer nMonth;
    @SerializedName("nYear")
    @Expose
    private Integer nYear;
    @SerializedName("sMonthPhoto")
    @Expose
    private String sMonthPhoto;
    private final static long serialVersionUID = 5957650357749456823L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSMonthYear() {
        return sMonthYear;
    }

    public void setSMonthYear(String sMonthYear) {
        this.sMonthYear = sMonthYear;
    }

    public Integer getNMonth() {
        return nMonth;
    }

    public void setNMonth(Integer nMonth) {
        this.nMonth = nMonth;
    }

    public Integer getNYear() {
        return nYear;
    }

    public void setNYear(Integer nYear) {
        this.nYear = nYear;
    }

    public String getSMonthPhoto() {
        return sMonthPhoto;
    }

    public void setSMonthPhoto(String sMonthPhoto) {
        this.sMonthPhoto = sMonthPhoto;
    }

}
