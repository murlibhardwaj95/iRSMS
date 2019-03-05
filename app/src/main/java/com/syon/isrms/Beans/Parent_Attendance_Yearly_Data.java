
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent_Attendance_Yearly_Data implements Serializable
{

    @SerializedName("lStudId")
    @Expose
    private Integer lStudId;
    @SerializedName("sStudName")
    @Expose
    private String sStudName;
    @SerializedName("nTotalPresent")
    @Expose
    private Integer nTotalPresent;
    @SerializedName("nTotalAttnd")
    @Expose
    private Integer nTotalAttnd;
    @SerializedName("dPresentPercent")
    @Expose
    private Double dPresentPercent;
    @SerializedName("lSessionId")
    @Expose
    private Integer lSessionId;
    private final static long serialVersionUID = -5488811831793473289L;

    public Integer getLStudId() {
        return lStudId;
    }

    public void setLStudId(Integer lStudId) {
        this.lStudId = lStudId;
    }

    public String getSStudName() {
        return sStudName;
    }

    public void setSStudName(String sStudName) {
        this.sStudName = sStudName;
    }

    public Integer getNTotalPresent() {
        return nTotalPresent;
    }

    public void setNTotalPresent(Integer nTotalPresent) {
        this.nTotalPresent = nTotalPresent;
    }

    public Integer getNTotalAttnd() {
        return nTotalAttnd;
    }

    public void setNTotalAttnd(Integer nTotalAttnd) {
        this.nTotalAttnd = nTotalAttnd;
    }

    public Double getDPresentPercent() {
        return dPresentPercent;
    }

    public void setDPresentPercent(Double dPresentPercent) {
        this.dPresentPercent = dPresentPercent;
    }

    public Integer getLSessionId() {
        return lSessionId;
    }

    public void setLSessionId(Integer lSessionId) {
        this.lSessionId = lSessionId;
    }

}
