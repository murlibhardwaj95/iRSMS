
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CircularBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<CircualrData> data = null;
    private final static long serialVersionUID = -1430256364948872395L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CircualrData> getData() {
        return data;
    }

    public void setData(List<CircualrData> data) {
        this.data = data;
    }

}
