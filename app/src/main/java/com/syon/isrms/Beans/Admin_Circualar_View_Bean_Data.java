
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Circualar_View_Bean_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("dtCirDate")
    @Expose
    private String dtCirDate;
    @SerializedName("sCirDate")
    @Expose
    private String sCirDate;
    @SerializedName("sTitle")
    @Expose
    private String sTitle;
    @SerializedName("sDescp")
    @Expose
    private String sDescp;
    @SerializedName("nAprvStatus")
    @Expose
    private Integer nAprvStatus;
    @SerializedName("sAprvStatus")
    @Expose
    private String sAprvStatus;
    @SerializedName("sAttachment")
    @Expose
    private String sAttachment;
    private final static long serialVersionUID = -3591206529708850663L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getDtCirDate() {
        return dtCirDate;
    }

    public void setDtCirDate(String dtCirDate) {
        this.dtCirDate = dtCirDate;
    }

    public String getSCirDate() {
        return sCirDate;
    }

    public void setSCirDate(String sCirDate) {
        this.sCirDate = sCirDate;
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

    public Integer getNAprvStatus() {
        return nAprvStatus;
    }

    public void setNAprvStatus(Integer nAprvStatus) {
        this.nAprvStatus = nAprvStatus;
    }

    public String getSAprvStatus() {
        return sAprvStatus;
    }

    public void setSAprvStatus(String sAprvStatus) {
        this.sAprvStatus = sAprvStatus;
    }

    public String getSAttachment() {
        return sAttachment;
    }

    public void setSAttachment(String sAttachment) {
        this.sAttachment = sAttachment;
    }

}
