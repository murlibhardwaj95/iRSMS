
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent_Attendance_Monthly_Bean_Data implements Serializable
{

    @SerializedName("lStudId")
    @Expose
    private Integer lStudId;
    @SerializedName("sStudName")
    @Expose
    private String sStudName;
    @SerializedName("nMonthNo")
    @Expose
    private Integer nMonthNo;
    @SerializedName("nYear")
    @Expose
    private Integer nYear;
    @SerializedName("nMonthPresent")
    @Expose
    private Integer nMonthPresent;
    @SerializedName("nMonthDutyLeave")
    @Expose
    private Integer nMonthDutyLeave;
    @SerializedName("nMonthAbsent")
    @Expose
    private Integer nMonthAbsent;
    @SerializedName("nMonthLeave")
    @Expose
    private Integer nMonthLeave;
    @SerializedName("nMonthMedical")
    @Expose
    private Integer nMonthMedical;
    @SerializedName("nMonthHoliday")
    @Expose
    private Integer nMonthHoliday;
    @SerializedName("nMonthNA")
    @Expose
    private Integer nMonthNA;
    @SerializedName("nMonthTotPresent")
    @Expose
    private Integer nMonthTotPresent;
    @SerializedName("nMonthTotAttnd")
    @Expose
    private Integer nMonthTotAttnd;
    @SerializedName("dMonthPresentPercent")
    @Expose
    private Double dMonthPresentPercent;
    @SerializedName("lSessionId")
    @Expose
    private Integer lSessionId;
    private final static long serialVersionUID = 7205871562330507961L;

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

    public Integer getNMonthNo() {
        return nMonthNo;
    }

    public void setNMonthNo(Integer nMonthNo) {
        this.nMonthNo = nMonthNo;
    }

    public Integer getNYear() {
        return nYear;
    }

    public void setNYear(Integer nYear) {
        this.nYear = nYear;
    }

    public Integer getNMonthPresent() {
        return nMonthPresent;
    }

    public void setNMonthPresent(Integer nMonthPresent) {
        this.nMonthPresent = nMonthPresent;
    }

    public Integer getNMonthDutyLeave() {
        return nMonthDutyLeave;
    }

    public void setNMonthDutyLeave(Integer nMonthDutyLeave) {
        this.nMonthDutyLeave = nMonthDutyLeave;
    }

    public Integer getNMonthAbsent() {
        return nMonthAbsent;
    }

    public void setNMonthAbsent(Integer nMonthAbsent) {
        this.nMonthAbsent = nMonthAbsent;
    }

    public Integer getNMonthLeave() {
        return nMonthLeave;
    }

    public void setNMonthLeave(Integer nMonthLeave) {
        this.nMonthLeave = nMonthLeave;
    }

    public Integer getNMonthMedical() {
        return nMonthMedical;
    }

    public void setNMonthMedical(Integer nMonthMedical) {
        this.nMonthMedical = nMonthMedical;
    }

    public Integer getNMonthHoliday() {
        return nMonthHoliday;
    }

    public void setNMonthHoliday(Integer nMonthHoliday) {
        this.nMonthHoliday = nMonthHoliday;
    }

    public Integer getNMonthNA() {
        return nMonthNA;
    }

    public void setNMonthNA(Integer nMonthNA) {
        this.nMonthNA = nMonthNA;
    }

    public Integer getNMonthTotPresent() {
        return nMonthTotPresent;
    }

    public void setNMonthTotPresent(Integer nMonthTotPresent) {
        this.nMonthTotPresent = nMonthTotPresent;
    }

    public Integer getNMonthTotAttnd() {
        return nMonthTotAttnd;
    }

    public void setNMonthTotAttnd(Integer nMonthTotAttnd) {
        this.nMonthTotAttnd = nMonthTotAttnd;
    }

    public Double getDMonthPresentPercent() {
        return dMonthPresentPercent;
    }

    public void setDMonthPresentPercent(Double dMonthPresentPercent) {
        this.dMonthPresentPercent = dMonthPresentPercent;
    }

    public Integer getLSessionId() {
        return lSessionId;
    }

    public void setLSessionId(Integer lSessionId) {
        this.lSessionId = lSessionId;
    }

}
