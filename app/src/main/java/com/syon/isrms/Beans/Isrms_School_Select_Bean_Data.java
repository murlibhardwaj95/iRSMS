
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Isrms_School_Select_Bean_Data implements Serializable
{

    @SerializedName("sSch_Code")
    @Expose
    private String sSchCode;
    @SerializedName("sSch_Name")
    @Expose
    private String sSchName;
    @SerializedName("sCity")
    @Expose
    private String sCity;
    @SerializedName("sBase_URL")
    @Expose
    private String sBaseURL;
    @SerializedName("sLogo_Path")
    @Expose
    private String sLogoPath;
    @SerializedName("sMobAppVersion")
    @Expose
    private String sMobAppVersion;
    @SerializedName("nVersionFor")
    @Expose
    private Integer nVersionFor;
    @SerializedName("dtMod_Date")
    @Expose
    private String dtModDate;
    private final static long serialVersionUID = -8439895086476581043L;

    public String getSSchCode() {
        return sSchCode;
    }

    public void setSSchCode(String sSchCode) {
        this.sSchCode = sSchCode;
    }

    public String getSSchName() {
        return sSchName;
    }

    public void setSSchName(String sSchName) {
        this.sSchName = sSchName;
    }

    public String getSCity() {
        return sCity;
    }

    public void setSCity(String sCity) {
        this.sCity = sCity;
    }

    public String getSBaseURL() {
        return sBaseURL;
    }

    public void setSBaseURL(String sBaseURL) {
        this.sBaseURL = sBaseURL;
    }

    public String getSLogoPath() {
        return sLogoPath;
    }

    public void setSLogoPath(String sLogoPath) {
        this.sLogoPath = sLogoPath;
    }

    public String getSMobAppVersion() {
        return sMobAppVersion;
    }

    public void setSMobAppVersion(String sMobAppVersion) {
        this.sMobAppVersion = sMobAppVersion;
    }

    public Integer getNVersionFor() {
        return nVersionFor;
    }

    public void setNVersionFor(Integer nVersionFor) {
        this.nVersionFor = nVersionFor;
    }

    public String getDtModDate() {
        return dtModDate;
    }

    public void setDtModDate(String dtModDate) {
        this.dtModDate = dtModDate;
    }

}
