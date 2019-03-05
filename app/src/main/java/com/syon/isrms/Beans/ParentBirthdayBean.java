
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ParentBirthdayBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Parent_Birthday_Bean_Data> data = null;
    private final static long serialVersionUID = -8470776276083917318L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Parent_Birthday_Bean_Data> getData() {
        return data;
    }

    public void setData(List<Parent_Birthday_Bean_Data> data) {
        this.data = data;
    }

}
