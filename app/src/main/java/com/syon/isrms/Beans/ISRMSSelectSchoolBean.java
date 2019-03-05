
package com.syon.isrms.Beans;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ISRMSSelectSchoolBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Isrms_School_Select_Bean_Data> data = null;
    private final static long serialVersionUID = -6373045835521466475L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Isrms_School_Select_Bean_Data> getData() {
        return data;
    }

    public void setData(List<Isrms_School_Select_Bean_Data> data) {
        this.data = data;
    }

}
