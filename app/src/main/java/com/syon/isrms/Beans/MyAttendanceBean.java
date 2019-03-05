
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MyAttendanceBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<MyAttendance_Data> data = null;
    private final static long serialVersionUID = -7767665000791783761L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MyAttendance_Data> getData() {
        return data;
    }

    public void setData(List<MyAttendance_Data> data) {
        this.data = data;
    }

}
