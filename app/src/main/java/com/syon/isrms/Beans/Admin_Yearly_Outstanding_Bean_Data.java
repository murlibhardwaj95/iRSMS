
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Yearly_Outstanding_Bean_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("lFHeadId")
    @Expose
    private Integer lFHeadId;
    @SerializedName("sFHeadName")
    @Expose
    private String sFHeadName;
    @SerializedName("sFHeadAmt")
    @Expose
    private String sFHeadAmt;
    private final static long serialVersionUID = -1571319768372801421L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getLFHeadId() {
        return lFHeadId;
    }

    public void setLFHeadId(Integer lFHeadId) {
        this.lFHeadId = lFHeadId;
    }

    public String getSFHeadName() {
        return sFHeadName;
    }

    public void setSFHeadName(String sFHeadName) {
        this.sFHeadName = sFHeadName;
    }

    public String getSFHeadAmt() {
        return sFHeadAmt;
    }

    public void setSFHeadAmt(String sFHeadAmt) {
        this.sFHeadAmt = sFHeadAmt;
    }

}
