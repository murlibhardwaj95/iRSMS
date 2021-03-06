
package com.syon.isrms.Beans;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminClassResultFillExamBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Admin_Class_Result_Fill_Exam_Bean_Data> data = null;
    private final static long serialVersionUID = -3737917579012694324L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Admin_Class_Result_Fill_Exam_Bean_Data> getData() {
        return data;
    }

    public void setData(List<Admin_Class_Result_Fill_Exam_Bean_Data> data) {
        this.data = data;
    }

}
