
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FillClassBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Fill_Class_Data> data = null;
    private final static long serialVersionUID = -6488872322411067826L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Fill_Class_Data> getData() {
        return data;
    }

    public void setData(List<Fill_Class_Data> data) {
        this.data = data;
    }

}
