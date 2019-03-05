
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Library_Status_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("sBookCode")
    @Expose
    private String sBookCode;
    @SerializedName("sBookTitle")
    @Expose
    private String sBookTitle;
    @SerializedName("sIssueDate")
    @Expose
    private String sIssueDate;
    @SerializedName("sReturnDate")
    @Expose
    private String sReturnDate;
    private final static long serialVersionUID = 7187551780840577189L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getSBookCode() {
        return sBookCode;
    }

    public void setSBookCode(String sBookCode) {
        this.sBookCode = sBookCode;
    }

    public String getSBookTitle() {
        return sBookTitle;
    }

    public void setSBookTitle(String sBookTitle) {
        this.sBookTitle = sBookTitle;
    }

    public String getSIssueDate() {
        return sIssueDate;
    }

    public void setSIssueDate(String sIssueDate) {
        this.sIssueDate = sIssueDate;
    }

    public String getSReturnDate() {
        return sReturnDate;
    }

    public void setSReturnDate(String sReturnDate) {
        this.sReturnDate = sReturnDate;
    }

}
