
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LibraryStatusBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Library_Status_Data> data = null;
    private final static long serialVersionUID = 7048654503407469292L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Library_Status_Data> getData() {
        return data;
    }

    public void setData(List<Library_Status_Data> data) {
        this.data = data;
    }

}
