package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Admin_Month_Salary_Slip implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("lMESDId")
    @Expose
    private Integer lMESDId;
    @SerializedName("nMonth")
    @Expose
    private Integer nMonth;
    @SerializedName("nYear")
    @Expose
    private Integer nYear;
    @SerializedName("sMonthYear")
    @Expose
    private String sMonthYear;
    @SerializedName("sBasic")
    @Expose
    private String sBasic;
    @SerializedName("sAllowance")
    @Expose
    private String sAllowance;
    @SerializedName("sGrossSalary")
    @Expose
    private String sGrossSalary;
    @SerializedName("sDeduction")
    @Expose
    private String sDeduction;
    @SerializedName("sNetSalary")
    @Expose
    private String sNetSalary;
    private final static long serialVersionUID = -7130202859580580161L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getLMESDId() {
        return lMESDId;
    }

    public void setLMESDId(Integer lMESDId) {
        this.lMESDId = lMESDId;
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

    public String getSMonthYear() {
        return sMonthYear;
    }

    public void setSMonthYear(String sMonthYear) {
        this.sMonthYear = sMonthYear;
    }

    public String getSBasic() {
        return sBasic;
    }

    public void setSBasic(String sBasic) {
        this.sBasic = sBasic;
    }

    public String getSAllowance() {
        return sAllowance;
    }

    public void setSAllowance(String sAllowance) {
        this.sAllowance = sAllowance;
    }

    public String getSGrossSalary() {
        return sGrossSalary;
    }

    public void setSGrossSalary(String sGrossSalary) {
        this.sGrossSalary = sGrossSalary;
    }

    public String getSDeduction() {
        return sDeduction;
    }

    public void setSDeduction(String sDeduction) {
        this.sDeduction = sDeduction;
    }

    public String getSNetSalary() {
        return sNetSalary;
    }

    public void setSNetSalary(String sNetSalary) {
        this.sNetSalary = sNetSalary;
    }

}
