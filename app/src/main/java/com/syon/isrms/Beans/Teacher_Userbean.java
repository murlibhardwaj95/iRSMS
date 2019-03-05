package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Teacher_Userbean implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Userbean_TeacherData> data = null;
    private final static long serialVersionUID = 861841610621447839L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Userbean_TeacherData> getData() {
        return data;
    }

    public void setData(List<Userbean_TeacherData> data) {
        this.data = data;
    }
}
