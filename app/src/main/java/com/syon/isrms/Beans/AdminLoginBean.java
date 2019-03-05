
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminLoginBean implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Admin_Login_Bean_Data data;
    private final static long serialVersionUID = 6878382073274512972L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Admin_Login_Bean_Data getData() {
        return data;
    }

    public void setData(Admin_Login_Bean_Data admin_login_bean_data) {
        this.data = data;
    }

}
