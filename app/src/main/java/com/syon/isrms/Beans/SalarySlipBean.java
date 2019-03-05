
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SalarySlipBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<SalarySlipData> data = null;
    private final static long serialVersionUID = 1852395036870032542L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SalarySlipData> getData() {
        return data;
    }

    public void setData(List<SalarySlipData> data) {
        this.data = data;
    }

}
