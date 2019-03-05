
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_News_View_Bean_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
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
    @SerializedName("nAprvStatus")
    @Expose
    private Integer nAprvStatus;
    @SerializedName("sAprvStatus")
    @Expose
    private String sAprvStatus;
    @SerializedName("sAttachment")
    @Expose
    private String sAttachment;
    private final static long serialVersionUID = 253252372378680197L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

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
