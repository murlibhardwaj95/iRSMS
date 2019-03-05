
package com.syon.isrms.Beans;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminClassResultSelectedExamBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Admin_Class_Result_Selected_Exam_Data> data = null;
    private final static long serialVersionUID = 8740840882954361949L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Admin_Class_Result_Selected_Exam_Data> getData() {
        return data;
    }

    public void setData(List<Admin_Class_Result_Selected_Exam_Data> data) {
        this.data = data;
    }

}
