package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Admin_DetailClik_Data implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sPercentRange")
    @Expose
    private String sPercentRange;
    @SerializedName("averageGrade")
    @Expose
    private String averageGrade;
    @SerializedName("studCntPercent")
    @Expose
    private String studCntPercent;
    @SerializedName("dStudCntPercent")
    @Expose
    private Double dStudCntPercent;
    private final static long serialVersionUID = -2489565635048456773L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSPercentRange() {
        return sPercentRange;
    }

    public void setSPercentRange(String sPercentRange) {
        this.sPercentRange = sPercentRange;
    }

    public String getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getStudCntPercent() {
        return studCntPercent;
    }

    public void setStudCntPercent(String studCntPercent) {
        this.studCntPercent = studCntPercent;
    }

    public Double getDStudCntPercent() {
        return dStudCntPercent;
    }

    public void setDStudCntPercent(Double dStudCntPercent) {
        this.dStudCntPercent = dStudCntPercent;
    }
}
