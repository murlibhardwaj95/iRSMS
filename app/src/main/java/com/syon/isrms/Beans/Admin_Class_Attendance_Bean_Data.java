
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Class_Attendance_Bean_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sClass")
    @Expose
    private String sClass;
    @SerializedName("dPresentPercent")
    @Expose
    private Double dPresentPercent;
    @SerializedName("dAbsentPercent")
    @Expose
    private Double dAbsentPercent;
    private final static long serialVersionUID = -7840826006993418777L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSClass() {
        return sClass;
    }

    public void setSClass(String sClass) {
        this.sClass = sClass;
    }

    public Double getDPresentPercent() {
        return dPresentPercent;
    }

    public void setDPresentPercent(Double dPresentPercent) {
        this.dPresentPercent = dPresentPercent;
    }

    public Double getDAbsentPercent() {
        return dAbsentPercent;
    }

    public void setDAbsentPercent(Double dAbsentPercent) {
        this.dAbsentPercent = dAbsentPercent;
    }

}
