
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Fill_Class_Data implements Serializable
{

    @SerializedName("lClassId")
    @Expose
    private Integer lClassId;
    @SerializedName("sClassName")
    @Expose
    private String sClassName;
    @SerializedName("nClsSrNo")
    @Expose
    private Integer nClsSrNo;
    private final static long serialVersionUID = -5459414477054089684L;

    public Integer getLClassId() {
        return lClassId;
    }

    public void setLClassId(Integer lClassId) {
        this.lClassId = lClassId;
    }

    public String getSClassName() {
        return sClassName;
    }

    public void setSClassName(String sClassName) {
        this.sClassName = sClassName;
    }

    public Integer getNClsSrNo() {
        return nClsSrNo;
    }

    public void setNClsSrNo(Integer nClsSrNo) {
        this.nClsSrNo = nClsSrNo;
    }

}
