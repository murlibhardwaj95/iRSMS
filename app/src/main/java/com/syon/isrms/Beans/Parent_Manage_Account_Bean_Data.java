package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parent_Manage_Account_Bean_Data implements Serializable
{

    @SerializedName("lUPIdNo")
    @Expose
    private Integer lUPIdNo;
    @SerializedName("lStudIdNo")
    @Expose
    private Integer lStudIdNo;
    @SerializedName("sUserName")
    @Expose
    private String sUserName;
    @SerializedName("sPassword")
    @Expose
    private String sPassword;
    @SerializedName("sStudName")
    @Expose
    private String sStudName;
    @SerializedName("sAdmNo")
    @Expose
    private String sAdmNo;
    @SerializedName("sClass")
    @Expose
    private String sClass;
    @SerializedName("sSection")
    @Expose
    private String sSection;
    @SerializedName("studPhoto")
    @Expose
    private String studPhoto;
    private final static long serialVersionUID = 4901119718839514176L;

    public Integer getLUPIdNo() {
        return lUPIdNo;
    }

    public void setLUPIdNo(Integer lUPIdNo) {
        this.lUPIdNo = lUPIdNo;
    }

    public Integer getLStudIdNo() {
        return lStudIdNo;
    }

    public void setLStudIdNo(Integer lStudIdNo) {
        this.lStudIdNo = lStudIdNo;
    }

    public String getSUserName() {
        return sUserName;
    }

    public void setSUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getSStudName() {
        return sStudName;
    }

    public void setSStudName(String sStudName) {
        this.sStudName = sStudName;
    }

    public String getSAdmNo() {
        return sAdmNo;
    }

    public void setSAdmNo(String sAdmNo) {
        this.sAdmNo = sAdmNo;
    }

    public String getSClass() {
        return sClass;
    }

    public void setSClass(String sClass) {
        this.sClass = sClass;
    }

    public String getSSection() {
        return sSection;
    }

    public void setSSection(String sSection) {
        this.sSection = sSection;
    }

    public String getStudPhoto() {
        return studPhoto;
    }

    public void setStudPhoto(String studPhoto) {
        this.studPhoto = studPhoto;
    }

}
