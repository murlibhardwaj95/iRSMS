package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Userbean_Parent_Class implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Userbean_Parent_ClassId> data = null;
    private final static long serialVersionUID = 861841610621447839L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Userbean_Parent_ClassId> getData() {
        return data;
    }

    public void setData(List<Userbean_Parent_ClassId> data) {
        this.data = data;
    }
}
