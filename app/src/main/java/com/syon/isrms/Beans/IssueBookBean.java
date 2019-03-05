
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class IssueBookBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Issued_Book_Data> data = null;
    private final static long serialVersionUID = -6037881003411770299L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Issued_Book_Data> getData() {
        return data;
    }

    public void setData(List<Issued_Book_Data> data) {
        this.data = data;
    }

}
