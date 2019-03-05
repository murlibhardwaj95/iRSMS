
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminChangePasswordBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Admin_Change_Password_Bean_Data data;
    private final static long serialVersionUID = -4957328760434051306L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Admin_Change_Password_Bean_Data getData() {
        return data;
    }

    public void setData(Admin_Change_Password_Bean_Data data) {
        this.data = data;
    }

}
