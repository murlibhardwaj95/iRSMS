
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FillSectionBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Fill_Section_Data> data = null;
    private final static long serialVersionUID = -3677525911552898928L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Fill_Section_Data> getData() {
        return data;
    }

    public void setData(List<Fill_Section_Data> data) {
        this.data = data;
    }

}
