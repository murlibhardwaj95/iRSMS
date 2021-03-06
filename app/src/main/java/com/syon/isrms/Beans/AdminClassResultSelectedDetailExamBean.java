
package com.syon.isrms.Beans;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminClassResultSelectedDetailExamBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Admin_Class_Result_Selected_Deatil_Exam_Bean> data = null;
    private final static long serialVersionUID = 7231492929169784443L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Admin_Class_Result_Selected_Deatil_Exam_Bean> getData() {
        return data;
    }

    public void setData(List<Admin_Class_Result_Selected_Deatil_Exam_Bean> data) {
        this.data = data;
    }

}
