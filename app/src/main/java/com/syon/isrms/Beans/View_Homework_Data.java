
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class View_Homework_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("dtHWDate")
    @Expose
    private String dtHWDate;
    @SerializedName("sClass")
    @Expose
    private String sClass;
    @SerializedName("sSubject")
    @Expose
    private String sSubject;
    @SerializedName("dtComplDate")
    @Expose
    private String dtComplDate;
    @SerializedName("sTitle")
    @Expose
    private String sTitle;
    private final static long serialVersionUID = 2765487336864099368L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public String getDtHWDate() {
        return dtHWDate;
    }

    public void setDtHWDate(String dtHWDate) {
        this.dtHWDate = dtHWDate;
    }

    public String getSClass() {
        return sClass;
    }

    public void setSClass(String sClass) {
        this.sClass = sClass;
    }

    public String getSSubject() {
        return sSubject;
    }

    public void setSSubject(String sSubject) {
        this.sSubject = sSubject;
    }

    public String getDtComplDate() {
        return dtComplDate;
    }

    public void setDtComplDate(String dtComplDate) {
        this.dtComplDate = dtComplDate;
    }

    public String getSTitle() {
        return sTitle;
    }

    public void setSTitle(String sTitle) {
        this.sTitle = sTitle;
    }

}
