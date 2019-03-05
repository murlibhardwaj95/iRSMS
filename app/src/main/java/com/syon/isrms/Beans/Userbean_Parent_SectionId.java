package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userbean_Parent_SectionId implements Serializable{

    @SerializedName("lSecId")
    @Expose
    private Integer lSecId;
    @SerializedName("sSecName")
    @Expose
    private String sSecName;
    private final static long serialVersionUID = -3300435422494351616L;

    public Integer getLSecId() {
        return lSecId;
    }

    public void setLSecId(Integer lSecId) {
        this.lSecId = lSecId;
    }

    public String getSSecName() {
        return sSecName;
    }

    public void setSSecName(String sSecName) {
        this.sSecName = sSecName;
    }
}
