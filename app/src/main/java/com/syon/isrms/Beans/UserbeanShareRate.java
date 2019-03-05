
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserbeanShareRate implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("shareApp")
    @Expose
    private String shareApp;
    @SerializedName("rateUs")
    @Expose
    private String rateUs;
    @SerializedName("schoolWebsite")
    @Expose
    private String schoolWebsite;
    @SerializedName("schoolFacebook")
    @Expose
    private String schoolFacebook;
    private final static long serialVersionUID = -784056760739279243L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShareApp() {
        return shareApp;
    }

    public void setShareApp(String shareApp) {
        this.shareApp = shareApp;
    }

    public String getRateUs() {
        return rateUs;
    }

    public void setRateUs(String rateUs) {
        this.rateUs = rateUs;
    }

    public String getSchoolWebsite() {
        return schoolWebsite;
    }

    public void setSchoolWebsite(String schoolWebsite) {
        this.schoolWebsite = schoolWebsite;
    }

    public String getSchoolFacebook() {
        return schoolFacebook;
    }

    public void setSchoolFacebook(String schoolFacebook) {
        this.schoolFacebook = schoolFacebook;
    }
}
