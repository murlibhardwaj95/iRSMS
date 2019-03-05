
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ParentMedicalRecordBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Parent_Medical_Record_Bean_Data> data = null;
    private final static long serialVersionUID = 5826136839656449827L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Parent_Medical_Record_Bean_Data> getData() {
        return data;
    }

    public void setData(List<Parent_Medical_Record_Bean_Data> data) {
        this.data = data;
    }

}
