
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminYearlyAcadmicReportBean implements Serializable
{

    @SerializedName("nEnqCnt")
    @Expose
    private Integer nEnqCnt;
    @SerializedName("nProsSaleCnt")
    @Expose
    private Integer nProsSaleCnt;
    @SerializedName("nRegCnt")
    @Expose
    private Integer nRegCnt;
    @SerializedName("nAdmCnt")
    @Expose
    private Integer nAdmCnt;
    @SerializedName("nTCCnt")
    @Expose
    private Integer nTCCnt;
    @SerializedName("nWOCnt")
    @Expose
    private Integer nWOCnt;
    private final static long serialVersionUID = 7064195410957466188L;

    public Integer getNEnqCnt() {
        return nEnqCnt;
    }

    public void setNEnqCnt(Integer nEnqCnt) {
        this.nEnqCnt = nEnqCnt;
    }

    public Integer getNProsSaleCnt() {
        return nProsSaleCnt;
    }

    public void setNProsSaleCnt(Integer nProsSaleCnt) {
        this.nProsSaleCnt = nProsSaleCnt;
    }

    public Integer getNRegCnt() {
        return nRegCnt;
    }

    public void setNRegCnt(Integer nRegCnt) {
        this.nRegCnt = nRegCnt;
    }

    public Integer getNAdmCnt() {
        return nAdmCnt;
    }

    public void setNAdmCnt(Integer nAdmCnt) {
        this.nAdmCnt = nAdmCnt;
    }

    public Integer getNTCCnt() {
        return nTCCnt;
    }

    public void setNTCCnt(Integer nTCCnt) {
        this.nTCCnt = nTCCnt;
    }

    public Integer getNWOCnt() {
        return nWOCnt;
    }

    public void setNWOCnt(Integer nWOCnt) {
        this.nWOCnt = nWOCnt;
    }

}
