
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EventCalenderGroupBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Event_Celander_Data> data = null;
    private final static long serialVersionUID = 6203260331888199455L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Event_Celander_Data> getData() {
        return data;
    }

    public void setData(List<Event_Celander_Data> data) {
        this.data = data;
    }

}
