package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class Userbean_MySubjectTeacher_Main implements Serializable {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Userbean_MySubjectTeacher_Data> data = null;
    private final static long serialVersionUID = 861841610621447839L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Userbean_MySubjectTeacher_Data> getData() {
        return data;
    }

    public void setData(List<Userbean_MySubjectTeacher_Data> data) {
        this.data = data;
    }
}
