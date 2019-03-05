
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Class_Result_Selected_Deatil_Exam_Bean implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sPercentRange")
    @Expose
    private String sPercentRange;
    @SerializedName("sAverageGrade")
    @Expose
    private String sAverageGrade;
    @SerializedName("sStudCntPercent")
    @Expose
    private String sStudCntPercent;
    @SerializedName("dStudCntPercent")
    @Expose
    private Double dStudCntPercent;
    private final static long serialVersionUID = 8927204391534972239L;

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

    public String getSAverageGrade() {
        return sAverageGrade;
    }

    public void setSAverageGrade(String sAverageGrade) {
        this.sAverageGrade = sAverageGrade;
    }

    public String getSStudCntPercent() {
        return sStudCntPercent;
    }

    public void setSStudCntPercent(String sStudCntPercent) {
        this.sStudCntPercent = sStudCntPercent;
    }

    public Double getDStudCntPercent() {
        return dStudCntPercent;
    }

    public void setDStudCntPercent(Double dStudCntPercent) {
        this.dStudCntPercent = dStudCntPercent;
    }

}
