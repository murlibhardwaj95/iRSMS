package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_ParentInbox_data implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("fromUser")
    @Expose
    private String fromUser;
    @SerializedName("sSubject")
    @Expose
    private String sSubject;
    @SerializedName("sDescription")
    @Expose
    private String sDescription;
    @SerializedName("sAttachment")
    @Expose
    private String sAttachment;
    @SerializedName("dtDate")
    @Expose
    private String dtDate;
    @SerializedName("sDate")
    @Expose
    private String sDate;
    @SerializedName("lUCIdNo")
    @Expose
    private Integer lUCIdNo;
    @SerializedName("sFileName")
    @Expose
    private String sFileName;
    private final static long serialVersionUID = -5485898789299808799L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getSSubject() {
        return sSubject;
    }

    public void setSSubject(String sSubject) {
        this.sSubject = sSubject;
    }

    public String getSDescription() {
        return sDescription;
    }

    public void setSDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    public String getSAttachment() {
        return sAttachment;
    }

    public void setSAttachment(String sAttachment) {
        this.sAttachment = sAttachment;
    }

    public String getDtDate() {
        return dtDate;
    }

    public void setDtDate(String dtDate) {
        this.dtDate = dtDate;
    }

    public String getSDate() {
        return sDate;
    }

    public void setSDate(String sDate) {
        this.sDate = sDate;
    }

    public Integer getLUCIdNo() {
        return lUCIdNo;
    }

    public void setLUCIdNo(Integer lUCIdNo) {
        this.lUCIdNo = lUCIdNo;
    }

    public String getSFileName() {
        return sFileName;
    }

    public void setSFileName(String sFileName) {
        this.sFileName = sFileName;
    }
}
