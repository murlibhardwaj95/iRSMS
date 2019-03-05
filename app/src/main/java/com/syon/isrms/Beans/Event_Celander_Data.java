
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Event_Celander_Data implements Serializable
{

    @SerializedName("grouphead")
    @Expose
    private String grouphead;
    @SerializedName("groupphoto")
    @Expose
    private String groupphoto;
    @SerializedName("data")
    @Expose
    private List<Event_calender_Inner_Data> data = null;
    private final static long serialVersionUID = -5073582891169157006L;

    public String getGrouphead() {
        return grouphead;
    }

    public void setGrouphead(String grouphead) {
        this.grouphead = grouphead;
    }

    public String getGroupphoto() {
        return groupphoto;
    }

    public void setGroupphoto(String groupphoto) {
        this.groupphoto = groupphoto;
    }

    public List<Event_calender_Inner_Data> getData() {
        return data;
    }

    public void setData(List<Event_calender_Inner_Data> data) {
        this.data = data;
    }

}
