
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ParentHomeworkDateBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Parent_Homework_Date_Data_Bean> data = null;
    private final static long serialVersionUID = -210876121079586773L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Parent_Homework_Date_Data_Bean> getData() {
        return data;
    }

    public void setData(List<Parent_Homework_Date_Data_Bean> data) {
        this.data = data;
    }

}
