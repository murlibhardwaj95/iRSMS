package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_Parent_ClassId implements Serializable {

    @SerializedName("lClassId")
    @Expose
    private Integer lClassId;
    @SerializedName("sClassName")
    @Expose
    private String sClassName;
    @SerializedName("nClsSrNo")
    @Expose
    private Integer nClsSrNo;
    private final static long serialVersionUID = 42455224030544031L;

    public String getLClassId() {
        return String.valueOf(lClassId);
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
