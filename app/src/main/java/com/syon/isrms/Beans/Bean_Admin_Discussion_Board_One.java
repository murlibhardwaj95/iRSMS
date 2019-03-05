package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Bean_Admin_Discussion_Board_One implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Bean_Admin_Discussion_Board_Two> data = null;
    private final static long serialVersionUID = 861841610621447839L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Bean_Admin_Discussion_Board_Two> getData() {
        return data;
    }

    public void setData(List<Bean_Admin_Discussion_Board_Two> data) {
        this.data = data;
    }

}
