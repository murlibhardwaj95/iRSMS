
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent_Attendance_Daywise_Bean_Data implements Serializable
{

    @SerializedName("srNo")
    @Expose
    private Integer srNo;
    @SerializedName("studId")
    @Expose
    private Integer studId;
    @SerializedName("studName")
    @Expose
    private String studName;
    @SerializedName("attndDate")
    @Expose
    private String attndDate;
    @SerializedName("attndStatus")
    @Expose
    private String attndStatus;
    @SerializedName("sMonthName")
    @Expose
    private String sMonthName;
    private final static long serialVersionUID = 676638700595080762L;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getAttndDate() {
        return attndDate;
    }

    public void setAttndDate(String attndDate) {
        this.attndDate = attndDate;
    }

    public String getAttndStatus() {
        return attndStatus;
    }

    public void setAttndStatus(String attndStatus) {
        this.attndStatus = attndStatus;
    }

    public String getSMonthName() {
        return sMonthName;
    }

    public void setSMonthName(String sMonthName) {
        this.sMonthName = sMonthName;
    }

}
