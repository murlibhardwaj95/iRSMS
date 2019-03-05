
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Online_Payment_Bean_Data implements Serializable
{

    @SerializedName("lateFee")
    @Expose
    private Integer lateFee;
    @SerializedName("lORIdNo")
    @Expose
    private Integer lORIdNo;
    @SerializedName("select")
    @Expose
    private Integer select;
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("installment")
    @Expose
    private String installment;
    @SerializedName("dueAmount")
    @Expose
    private Integer dueAmount;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("nInstlNo")
    @Expose
    private Integer nInstlNo;
    private final static long serialVersionUID = -4814338799075737093L;

    public Integer getLateFee() {
        return lateFee;
    }

    public void setLateFee(Integer lateFee) {
        this.lateFee = lateFee;
    }

    public Integer getLORIdNo() {
        return lORIdNo;
    }

    public void setLORIdNo(Integer lORIdNo) {
        this.lORIdNo = lORIdNo;
    }

    public Integer getSelect() {
        return select;
    }

    public void setSelect(Integer select) {
        this.select = select;
    }

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public Integer getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Integer dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getNInstlNo() {
        return nInstlNo;
    }

    public void setNInstlNo(Integer nInstlNo) {
        this.nInstlNo = nInstlNo;
    }

}
