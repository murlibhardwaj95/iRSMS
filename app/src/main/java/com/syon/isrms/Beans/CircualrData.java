
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CircualrData implements Serializable
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
    @SerializedName("sAttachment")
    @Expose
    private String sAttachment;
    private final static long serialVersionUID = -7233468422055564824L;

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

    public String getSAttachment() {
        return sAttachment;
    }

    public void setSAttachment(String sAttachment) {
        this.sAttachment = sAttachment;
    }

}
