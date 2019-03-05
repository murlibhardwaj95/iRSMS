
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EventCalenderMonthlyBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Event_Calender_Monthly_Data> data = null;
    private final static long serialVersionUID = 1482583285888045458L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Event_Calender_Monthly_Data> getData() {
        return data;
    }

    public void setData(List<Event_Calender_Monthly_Data> data) {
        this.data = data;
    }

}
