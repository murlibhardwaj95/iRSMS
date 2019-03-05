package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_MyClassTeacher_Main implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Userbean_MyClassTeacher_Data data;
    private final static long serialVersionUID = 1268494552077252500L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Userbean_MyClassTeacher_Data getData() {
        return data;
    }

    public void setData(Userbean_MyClassTeacher_Data data) {
        this.data = data;
    }

}
