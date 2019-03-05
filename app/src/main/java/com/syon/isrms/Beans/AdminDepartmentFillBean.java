
package com.syon.isrms.Beans;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminDepartmentFillBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Admin_Department_Fill_Bean_Data> data = null;
    private final static long serialVersionUID = -316664170668740767L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Admin_Department_Fill_Bean_Data> getData() {
        return data;
    }

    public void setData(List<Admin_Department_Fill_Bean_Data> data) {
        this.data = data;
    }

}
