
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Class_Fill_Bean_Data implements Serializable
{

    @SerializedName("lClassId")
    @Expose
    private Integer lClassId;
    @SerializedName("sClassName")
    @Expose
    private String sClassName;
    @SerializedName("nClassSrNo")
    @Expose
    private Integer nClassSrNo;
    @SerializedName("nSelect")
    @Expose
    private Integer nSelect;
    private final static long serialVersionUID = -4965920416354065286L;

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

    public Integer getNClassSrNo() {
        return nClassSrNo;
    }

    public void setNClassSrNo(Integer nClassSrNo) {
        this.nClassSrNo = nClassSrNo;
    }

    public Integer getNSelect() {
        return nSelect;
    }

    public void setNSelect(Integer nSelect) {
        this.nSelect = nSelect;
    }

}
