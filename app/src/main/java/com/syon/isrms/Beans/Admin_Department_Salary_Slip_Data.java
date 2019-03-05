package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Admin_Department_Salary_Slip_Data implements Serializable {

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sDeptName")
    @Expose
    private String sDeptName;
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
    private final static long serialVersionUID = -7104309446801082847L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSDeptName() {
        return sDeptName;
    }

    public void setSDeptName(String sDeptName) {
        this.sDeptName = sDeptName;
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
