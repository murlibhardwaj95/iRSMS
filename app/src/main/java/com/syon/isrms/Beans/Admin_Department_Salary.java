package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Admin_Department_Salary implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Admin_Department_Salary_Slip_Data> data = null;
    private final static long serialVersionUID = 861841610621447839L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Admin_Department_Salary_Slip_Data> getData() {
        return data;
    }

    public void setData(List<Admin_Department_Salary_Slip_Data> data) {
        this.data = data;
    }
}
