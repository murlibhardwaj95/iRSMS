
package com.syon.isrms.Beans;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminClassTeacherResultFillTeacherBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Admin_Class_Teacher_Result_Fill_Teacher_Data> data = null;
    private final static long serialVersionUID = 3772459870855255766L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Admin_Class_Teacher_Result_Fill_Teacher_Data> getData() {
        return data;
    }

    public void setData(List<Admin_Class_Teacher_Result_Fill_Teacher_Data> data) {
        this.data = data;
    }

}
