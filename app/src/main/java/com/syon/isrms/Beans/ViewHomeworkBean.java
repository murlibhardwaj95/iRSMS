
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ViewHomeworkBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<View_Homework_Data> data = null;
    private final static long serialVersionUID = -5022974588035022993L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<View_Homework_Data> getData() {
        return data;
    }

    public void setData(List<View_Homework_Data> data) {
        this.data = data;
    }

}
