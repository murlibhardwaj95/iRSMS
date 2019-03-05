
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SalarySlipData implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sMonth")
    @Expose
    private String sMonth;
    @SerializedName("lYear")
    @Expose
    private Integer lYear;
    @SerializedName("wd")
    @Expose
    private Integer wd;
    @SerializedName("mBasic")
    @Expose
    private Double mBasic;
    @SerializedName("mAllowance")
    @Expose
    private Double mAllowance;
    @SerializedName("mGrossSal")
    @Expose
    private Double mGrossSal;
    @SerializedName("mDeduction")
    @Expose
    private Double mDeduction;
    @SerializedName("mNetSal")
    @Expose
    private Double mNetSal;
    @SerializedName("lMonth")
    @Expose
    private Integer lMonth;
    @SerializedName("lESMId")
    @Expose
    private Integer lESMId;
    @SerializedName("sAttachment")
    @Expose
    private String sAttachment;
    private final static long serialVersionUID = -1693308996400732836L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSMonth() {
        return sMonth;
    }

    public void setSMonth(String sMonth) {
        this.sMonth = sMonth;
    }

    public Integer getLYear() {
        return lYear;
    }

    public void setLYear(Integer lYear) {
        this.lYear = lYear;
    }

    public Integer getWd() {
        return wd;
    }

    public void setWd(Integer wd) {
        this.wd = wd;
    }

    public Double getMBasic() {
        return mBasic;
    }

    public void setMBasic(Double mBasic) {
        this.mBasic = mBasic;
    }

    public Double getMAllowance() {
        return mAllowance;
    }

    public void setMAllowance(Double mAllowance) {
        this.mAllowance = mAllowance;
    }

    public Double getMGrossSal() {
        return mGrossSal;
    }

    public void setMGrossSal(Double mGrossSal) {
        this.mGrossSal = mGrossSal;
    }

    public Double getMDeduction() {
        return mDeduction;
    }

    public void setMDeduction(Double mDeduction) {
        this.mDeduction = mDeduction;
    }

    public Double getMNetSal() {
        return mNetSal;
    }

    public void setMNetSal(Double mNetSal) {
        this.mNetSal = mNetSal;
    }

    public Integer getLMonth() {
        return lMonth;
    }

    public void setLMonth(Integer lMonth) {
        this.lMonth = lMonth;
    }

    public Integer getLESMId() {
        return lESMId;
    }

    public void setLESMId(Integer lESMId) {
        this.lESMId = lESMId;
    }

    public String getSAttachment() {
        return sAttachment;
    }

    public void setSAttachment(String sAttachment) {
        this.sAttachment = sAttachment;
    }

}
