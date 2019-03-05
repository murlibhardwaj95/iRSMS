
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChangePassBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Change_Pass_Data data;
    private final static long serialVersionUID = -2066028645286165846L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Change_Pass_Data getData() {
        return data;
    }

    public void setData(Change_Pass_Data data) {
        this.data = data;
    }

}
