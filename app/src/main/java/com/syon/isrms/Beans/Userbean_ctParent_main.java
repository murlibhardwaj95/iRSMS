package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Userbean_ctParent_main implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Userbean_ctParent_Data> data = null;
    private final static long serialVersionUID = 861841610621447839L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Userbean_ctParent_Data> getData() {
        return data;
    }

    public void setData(List<Userbean_ctParent_Data> data) {
        this.data = data;
    }
}
