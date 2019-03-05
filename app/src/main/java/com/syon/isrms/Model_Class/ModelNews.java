package com.syon.isrms.Model_Class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ModelNews implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;





    @SerializedName("sLoginType")
    @Expose
    private String NEWS_ID;

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }


    public String getNEWS_ID() {
        return NEWS_ID;
    }

    public void setNEWS_ID(String NEWS_ID) {
        this.NEWS_ID = NEWS_ID;
    }


    public ModelNews() {
    }
}
