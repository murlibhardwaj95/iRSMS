
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Class_Result_Fill_Exam_Bean_Data implements Serializable
{

    @SerializedName("examId")
    @Expose
    private Integer examId;
    @SerializedName("examName")
    @Expose
    private String examName;
    private final static long serialVersionUID = -72332648181801526L;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

}
