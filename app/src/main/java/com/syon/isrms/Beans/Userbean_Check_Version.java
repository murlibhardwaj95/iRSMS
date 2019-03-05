package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_Check_Version implements Serializable {
    @SerializedName("sStatus")
    @Expose
    private String sStatus;
    @SerializedName("mobappversion")
    @Expose
    private String mobappversion;
    @SerializedName("url")
    @Expose
    private String url;
    private final static long serialVersionUID = -8781434031833470748L;

    public String getSStatus() {
        return sStatus;
    }

    public void setSStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public String getMobappversion() {
        return mobappversion;
    }

    public void setMobappversion(String mobappversion) {
        this.mobappversion = mobappversion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
