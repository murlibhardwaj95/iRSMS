package com.syon.isrms.Model_Class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gaurav on 13/02/2017.
 */
public class ResponseAttendances {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    ArrayList<SchoolAttendacnce> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<SchoolAttendacnce> getData() {
        return data;
    }

    public void setData(ArrayList<SchoolAttendacnce> data) {
        this.data = data;
    }
}
