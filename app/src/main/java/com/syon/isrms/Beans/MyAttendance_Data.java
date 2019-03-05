
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyAttendance_Data implements Serializable
{

    @SerializedName("sMonthYear")
    @Expose
    private String sMonthYear;
    @SerializedName("sWD")
    @Expose
    private String sWD;
    @SerializedName("sPresent")
    @Expose
    private String sPresent;
    @SerializedName("sAbsent")
    @Expose
    private String sAbsent;
    @SerializedName("sDutyLeave")
    @Expose
    private String sDutyLeave;
    @SerializedName("sLeave")
    @Expose
    private String sLeave;
    @SerializedName("sLeaveDetail")
    @Expose
    private String sLeaveDetail;
    @SerializedName("nMonth")
    @Expose
    private Integer nMonth;
    @SerializedName("nYear")
    @Expose
    private Integer nYear;
    private final static long serialVersionUID = 289319490291884803L;

    public String getSMonthYear() {
        return sMonthYear;
    }

    public void setSMonthYear(String sMonthYear) {
        this.sMonthYear = sMonthYear;
    }

    public String getSWD() {
        return sWD;
    }

    public void setSWD(String sWD) {
        this.sWD = sWD;
    }

    public String getSPresent() {
        return sPresent;
    }

    public void setSPresent(String sPresent) {
        this.sPresent = sPresent;
    }

    public String getSAbsent() {
        return sAbsent;
    }

    public void setSAbsent(String sAbsent) {
        this.sAbsent = sAbsent;
    }

    public String getSDutyLeave() {
        return sDutyLeave;
    }

    public void setSDutyLeave(String sDutyLeave) {
        this.sDutyLeave = sDutyLeave;
    }

    public String getSLeave() {
        return sLeave;
    }

    public void setSLeave(String sLeave) {
        this.sLeave = sLeave;
    }

    public String getSLeaveDetail() {
        return sLeaveDetail;
    }

    public void setSLeaveDetail(String sLeaveDetail) {
        this.sLeaveDetail = sLeaveDetail;
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

}
