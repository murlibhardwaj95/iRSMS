package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_News_Data implements Serializable {

    @SerializedName("dtNEDate")
    @Expose
    private String dtNEDate;
    @SerializedName("sNEDate")
    @Expose
    private String sNEDate;
    @SerializedName("sTitle")
    @Expose
    private String sTitle;
    @SerializedName("sDescp")
    @Expose
    private String sDescp;
    @SerializedName("sAttachment")
    @Expose
    private String sAttachment;
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    private final static long serialVersionUID = 4690519330089286202L;

    public String getDtNEDate() {
        return dtNEDate;
    }

    public void setDtNEDate(String dtNEDate) {
        this.dtNEDate = dtNEDate;
    }

    public String getSNEDate() {
        return sNEDate;
    }

    public void setSNEDate(String sNEDate) {
        this.sNEDate = sNEDate;
    }

    public String getSTitle() {
        return sTitle;
    }

    public void setSTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getSDescp() {
        return sDescp;
    }

    public void setSDescp(String sDescp) {
        this.sDescp = sDescp;
    }

    public String getSAttachment() {
        return sAttachment;
    }

    public void setSAttachment(String sAttachment) {
        this.sAttachment = sAttachment;
    }

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }
}
