
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FillSubjectBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Fill_Subject_Data> data = null;
    private final static long serialVersionUID = 702306710601362286L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Fill_Subject_Data> getData() {
        return data;
    }

    public void setData(List<Fill_Subject_Data> data) {
        this.data = data;
    }

}
