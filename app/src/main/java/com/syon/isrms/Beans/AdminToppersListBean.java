
package com.syon.isrms.Beans;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminToppersListBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Admin_Toppers_List_Data> data = null;
    private final static long serialVersionUID = -2881213831968268497L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Admin_Toppers_List_Data> getData() {
        return data;
    }

    public void setData(List<Admin_Toppers_List_Data> data) {
        this.data = data;
    }

}
