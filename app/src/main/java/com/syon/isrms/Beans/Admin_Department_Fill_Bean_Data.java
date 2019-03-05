
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Department_Fill_Bean_Data implements Serializable
{

    @SerializedName("lDeptId")
    @Expose
    private Integer lDeptId;
    @SerializedName("sDeptName")
    @Expose
    private String sDeptName;
    @SerializedName("nSelect")
    @Expose
    private Integer nSelect;
    private final static long serialVersionUID = -1262300513603844171L;

    public Integer getLDeptId() {
        return lDeptId;
    }

    public void setLDeptId(Integer lDeptId) {
        this.lDeptId = lDeptId;
    }

    public String getSDeptName() {
        return sDeptName;
    }

    public void setSDeptName(String sDeptName) {
        this.sDeptName = sDeptName;
    }

    public Integer getNSelect() {
        return nSelect;
    }

    public void setNSelect(Integer nSelect) {
        this.nSelect = nSelect;
    }

}
