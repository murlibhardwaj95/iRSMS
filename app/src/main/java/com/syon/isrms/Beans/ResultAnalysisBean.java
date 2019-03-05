
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResultAnalysisBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Result_Analasis_Data> data = null;
    private final static long serialVersionUID = -4332433449460773305L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result_Analasis_Data> getData() {
        return data;
    }

    public void setData(List<Result_Analasis_Data> data) {
        this.data = data;
    }

}
