
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Class_Result_Selected_Exam_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("lTblId")
    @Expose
    private Integer lTblId;
    @SerializedName("sClass")
    @Expose
    private String sClass;
    @SerializedName("nTotStud")
    @Expose
    private Integer nTotStud;
    @SerializedName("dOAPercent")
    @Expose
    private Double dOAPercent;
    @SerializedName("sOAGrade")
    @Expose
    private String sOAGrade;
    private final static long serialVersionUID = 3752251401792734135L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getLTblId() {
        return lTblId;
    }

    public void setLTblId(Integer lTblId) {
        this.lTblId = lTblId;
    }

    public String getSClass() {
        return sClass;
    }

    public void setSClass(String sClass) {
        this.sClass = sClass;
    }

    public Integer getNTotStud() {
        return nTotStud;
    }

    public void setNTotStud(Integer nTotStud) {
        this.nTotStud = nTotStud;
    }

    public Double getDOAPercent() {
        return dOAPercent;
    }

    public void setDOAPercent(Double dOAPercent) {
        this.dOAPercent = dOAPercent;
    }

    public String getSOAGrade() {
        return sOAGrade;
    }

    public void setSOAGrade(String sOAGrade) {
        this.sOAGrade = sOAGrade;
    }

}
