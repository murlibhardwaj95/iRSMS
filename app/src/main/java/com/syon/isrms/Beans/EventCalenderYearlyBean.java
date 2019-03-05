
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EventCalenderYearlyBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Event_calender_Yearly_Data> data = null;
    private final static long serialVersionUID = -2921704620507245190L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Event_calender_Yearly_Data> getData() {
        return data;
    }

    public void setData(List<Event_calender_Yearly_Data> data) {
        this.data = data;
    }

}
