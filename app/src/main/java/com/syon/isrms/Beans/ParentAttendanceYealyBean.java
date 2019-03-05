
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ParentAttendanceYealyBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Parent_Attendance_Yearly_Data> data = null;
    private final static long serialVersionUID = 5995448049866489418L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Parent_Attendance_Yearly_Data> getData() {
        return data;
    }

    public void setData(List<Parent_Attendance_Yearly_Data> data) {
        this.data = data;
    }

}
