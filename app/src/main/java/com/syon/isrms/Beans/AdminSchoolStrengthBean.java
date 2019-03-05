
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminSchoolStrengthBean implements Serializable
{

    @SerializedName("newAdmStud")
    @Expose
    private Integer newAdmStud;
    @SerializedName("rteNewAdm")
    @Expose
    private Integer rteNewAdm;
    @SerializedName("oldAdmStud")
    @Expose
    private Integer oldAdmStud;
    @SerializedName("rteOldAdm")
    @Expose
    private Integer rteOldAdm;
    @SerializedName("tcStud")
    @Expose
    private Integer tcStud;
    @SerializedName("rtetcStud")
    @Expose
    private Integer rtetcStud;
    @SerializedName("woStud")
    @Expose
    private Integer woStud;
    @SerializedName("rtewoStud")
    @Expose
    private Integer rtewoStud;
    @SerializedName("curStrength")
    @Expose
    private Integer curStrength;
    @SerializedName("rteCurStrength")
    @Expose
    private Integer rteCurStrength;
    private final static long serialVersionUID = 822683699219951428L;

    public Integer getNewAdmStud() {
        return newAdmStud;
    }

    public void setNewAdmStud(Integer newAdmStud) {
        this.newAdmStud = newAdmStud;
    }

    public Integer getRteNewAdm() {
        return rteNewAdm;
    }

    public void setRteNewAdm(Integer rteNewAdm) {
        this.rteNewAdm = rteNewAdm;
    }

    public Integer getOldAdmStud() {
        return oldAdmStud;
    }

    public void setOldAdmStud(Integer oldAdmStud) {
        this.oldAdmStud = oldAdmStud;
    }

    public Integer getRteOldAdm() {
        return rteOldAdm;
    }

    public void setRteOldAdm(Integer rteOldAdm) {
        this.rteOldAdm = rteOldAdm;
    }

    public Integer getTcStud() {
        return tcStud;
    }

    public void setTcStud(Integer tcStud) {
        this.tcStud = tcStud;
    }

    public Integer getRtetcStud() {
        return rtetcStud;
    }

    public void setRtetcStud(Integer rtetcStud) {
        this.rtetcStud = rtetcStud;
    }

    public Integer getWoStud() {
        return woStud;
    }

    public void setWoStud(Integer woStud) {
        this.woStud = woStud;
    }

    public Integer getRtewoStud() {
        return rtewoStud;
    }

    public void setRtewoStud(Integer rtewoStud) {
        this.rtewoStud = rtewoStud;
    }

    public Integer getCurStrength() {
        return curStrength;
    }

    public void setCurStrength(Integer curStrength) {
        this.curStrength = curStrength;
    }

    public Integer getRteCurStrength() {
        return rteCurStrength;
    }

    public void setRteCurStrength(Integer rteCurStrength) {
        this.rteCurStrength = rteCurStrength;
    }

}
