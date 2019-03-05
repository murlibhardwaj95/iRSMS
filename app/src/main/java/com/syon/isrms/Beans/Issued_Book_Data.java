
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Issued_Book_Data implements Serializable
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
    private final static long serialVersionUID = 3431643370888402685L;

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

}
